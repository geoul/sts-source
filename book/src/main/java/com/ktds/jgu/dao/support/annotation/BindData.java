package com.ktds.jgu.dao.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Dao에 쓸 예정.
public class BindData {

	private static List<String> columnNames;
	private static List<String> typesFieldNames;
	private static List<Class> primitive;
	
	static {
		primitive = new ArrayList<Class>();
		primitive.add(byte.class);
		primitive.add(short.class);
		primitive.add(int.class);
		primitive.add(long.class);
		primitive.add(double.class);
		primitive.add(float.class);
		primitive.add(char.class);
		primitive.add(boolean.class);
		primitive.add(String.class);
	}

	public static void startBind(ResultSet rs, Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		checkColumns(rs, fields, obj);
		bindData(rs, obj);
		
		typesFieldNames.clear();
		columnNames.clear();
	}
	
	public static void bindData(ResultSet rs, Object obj) {

		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] methods = obj.getClass().getDeclaredMethods();

		String column = "";
		String fieldName = "";
		boolean requires = false;
		Class<?> fieldType = null;

		Annotation[] annotataions = null;

		for (Field field : fields) {
			annotataions = field.getDeclaredAnnotations();
			fieldName = field.getName();
			fieldType = field.getType();
			for (Annotation annotation : annotataions) {
				if ( annotation.annotationType() == Types.class ) {
					requires = ((Types) annotation).requires();
					column = getFieldName(((Types) annotation).alias(), fieldName, rs);

					for (Method method : methods) {
						if(method.getName().toUpperCase().equals("SET" + fieldName.toUpperCase())) {
							if(fieldType == int.class) {
								try {
									method.invoke(obj, rs.getInt(column));
								} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
									if(requires) {
										throw new RuntimeException(e.getMessage() + " / field name["+fieldName+"], column name["+column+"]", e);
									}
								}
							}
							else if ( fieldType == double.class ) {
								try {
									method.invoke(obj, rs.getDouble(column));
								} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
									if(requires) {
										throw new RuntimeException(e.getMessage() + " / field name["+fieldName+"], column name["+column+"]", e);
									}
								}
							}
							else if ( fieldType == String.class ) {
								try {
									method.invoke(obj, rs.getString(column));
								} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
									if(requires) {
										throw new RuntimeException(e.getMessage() + " / field name["+fieldName+"], column name["+column+"]", e);
									}
								}
							}
						}
					}
				}

			}
		}

	}

	private static String convertString(String camelCase) {
		String smallLetters = "abcdefghijklmnopqrstuvwxyz";
		String bigLetters = "ABCEDFGHIJKLMNOPWRSTUVWXYZ";
		String column = "";

		char[] fieldLetters = camelCase.toCharArray();
		for (char c : fieldLetters) {
			if (smallLetters.indexOf(c + "") != -1) {
				column += (c + "").toUpperCase();
			} else if (bigLetters.indexOf(c + "") != -1) {
				column += ("_" + c);
			}
		}

		return column;
	}

	private static void checkColumns(ResultSet rs, Field[] fields, Object obj) {
		if(typesFieldNames == null || typesFieldNames.isEmpty()) {
			columnNames = getColumnNames(rs);
			typesFieldNames = getTypesFieldNames(fields, rs);

			for (String columnName : columnNames) {
				if (!typesFieldNames.contains(columnName)) {
					throw new RuntimeException("[" + obj.getClass().getSimpleName() + "]에서 [" + columnName + "]에 해당되는 @Types를 찾지 못했습니다.");
				}
			}
		}
	}

	private static List<String> getColumnNames(ResultSet rs) {
		List<String> columnNames = new ArrayList<String>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			for (int i = 0; i < columnCount; i++) {
				columnNames.add(metaData.getColumnLabel(i+1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		return columnNames;
	}

	
	private static List<String> getTypesFieldNames(Field[] fields, ResultSet rs) {

		Annotation[] annotataions = null;
		String fieldName = "";
		String column = "";

		List<String> typesFieldNames = new ArrayList<String>();

		for (Field field : fields) {
			annotataions = field.getDeclaredAnnotations();
			fieldName = field.getName();
			
			
			if ( !primitive.contains(field.getType()) ) {
				try {
					typesFieldNames.addAll(getTypesFieldNames(field.getType().newInstance().getClass().getDeclaredFields(), rs));
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			for (Annotation annotation : annotataions) {
				if (annotation.annotationType() == Types.class) {
					column = getFieldName(((Types) annotation).alias(), fieldName, rs);
					typesFieldNames.add(column);
				}
			}
		}

		return typesFieldNames;
	}

	private static String getFieldName(String column, String fieldName, ResultSet rs) {
		try {
			if(column.length() > 0) {
				rs.getString(column);
			}
			else {
				column = convertString(fieldName);
			}
		}
		catch(Exception e) {
			column = convertString(fieldName);
		}

		return column;
	}

}
