package blog.naver.com.common.web.pager;

public abstract class Pager {
	
	public static final boolean ORACLE = true;
	public static final boolean OTHER = false;
	
	/**
	 * 전체 게시물의 수
	 * 검색을 할 경우, 검색된 게시글의 수
	 */
	private int totalArticleCount;

	/**
	 * 한 페이지에 노출할 게시글의 수
	 * 기본 값으로 10을 가짐.
	 */
	protected int printArticle;
	/**
	 * 한 페이지 그룹에 노출할 페이지의 수
	 * 기본 값으로 10을 가짐.
	 */
	int printPage;

	/**
	 * 어떤 페이지 번호에서 노출할 게시글의 시작 번호
	 * 예 > 1페이지의 시작 번호는 1번
	 *     2페이지의 시작 번호는 11번
	 */
	protected int startArticleNumber;
	/**
	 * 어떤 페이지 번호에서 노출할 게시글의 마지막 번호
	 */
	protected int endArticleNumber;

	/**
	 * 전체 페이지의 수
	 */
	int totalPage;
	/**
	 * 전체 페이지 그룹의 수
	 */
	int totalGroup;

	/**
	 * 현재 노출 중인 페이지 그룹의 번호
	 */
	int nowGroupNumber;

	/**
	 * 현재 노출 중인 페이지 그룹의 시작 페이지 번호
	 */
	int groupStartPage;

	/**
	 * 현재 노출 중인 페이지 그룹의 다음 페이지 그룹 번호
	 */
	int nextGroupPageNumber;
	/**
	 * 현재 노출 중인 페이지 그룹의 이전 페이지 그룹 번호
	 */
	int prevGroupPageNumber;

	/**
	 * 현재 노출 중인 페이지 번호
	 */
	protected int pageNo;
	
	/**
	 * Paging 객체를 얻어온다.
	 * 한 페이지당 보여지는 게시글 수 10개
	 * 한 페이지당 보여지는 페이지 수 10개
	 * 로 기본 설정됨.
	 */
	public Pager() {
		this.printArticle = 10;
		this.printPage = 10;
	}
	
	public Pager(int printArticle, int printPage) {
		this.printArticle = printArticle;
		this.printPage = printPage;
	}
	
	public void setPageNumber(int pageNumber) {
		setPageNumber(pageNumber + "");
	}
	
	/**
	 * 요청된 페이지의 번호를 얻어온다.
	 * 1 페이지의 경우 0이 입력된다.
	 * 아무것도 입력하지 않았다면 0으로 기본 설정된다.
	 * @param pageNumber
	 */
	public void setPageNumber(String pageNumber) {
		this.pageNo = 0;
		try {
			this.pageNo = Integer.parseInt(pageNumber);
		} catch (NumberFormatException nfe) {
			this.pageNo = 0;
		}

		computeArticleNumbers();
		
		this.nowGroupNumber = this.pageNo / this.printPage;
		this.groupStartPage = (this.nowGroupNumber * this.printPage) + 1;

		this.nextGroupPageNumber = this.groupStartPage + this.printPage - 1;
		this.prevGroupPageNumber = this.groupStartPage - this.printPage - 1;
	}
	
	protected abstract void computeArticleNumbers();
	
	/**
	 * 조회하려는 조건(Query)의 총 게시물 수를 정의한다.
	 * @param count
	 */
	public void setTotalArticleCount(int count) {
		this.totalArticleCount = count;

		this.totalPage = (int) Math.ceil((double) this.totalArticleCount
				/ this.printArticle);
		this.totalGroup = (int) Math.ceil((double) this.totalPage
				/ this.printPage);
	}
	
	/**
	 * 조회하려는 조건(Query)의 총 게시물 수를 가져온다.
	 * @return
	 */
	public int getTotalArticleCount() {
		return this.totalArticleCount;
	}

	/**
	 * Query에서 사용될 탐색 시작 번호 
	 * Oracle의 경우 rownum의 시작 번호
	 * @return
	 */
	public int getStartArticleNumber() {
		return this.startArticleNumber;
	}
	
	/**
	 * Query에서 사용될 탐색 시작 번호를 정의한다.
	 * @param startArticleNumber
	 */
	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}
	
	/**
	 * Query에서 사용될 탐색 끝 번호를 정의한다.
	 * @param endArticleNumber
	 */
	public abstract void setEndArticleNumber(int endArticleNumber);

	/**
	 * Query에서 사용될 탐색 마지막 번호
	 * Oracle의 경우 rownum의 마지막 번호
	 * @return
	 */
	public abstract int getEndArticleNumber();
	
}
