package com.it.dao;

import com.it.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    //查询图书信息，根据图书的上架时间查询图书信息，查询新上架的5本图书
    @Select("select * from book where book_status!='3'\n" +
            " order by book_status asc, book_uploadtime desc limit 5")
    @Results(id = "bookMap", value = {
            @Result(id = true, column = "book_id", property = "id"),
            @Result(column = "book_name", property = "name"),
            @Result(column = "book_isbn", property = "isbn"),
            @Result(column = "book_press", property = "press"),
            @Result(column = "book_author", property = "author"),
            @Result(column = "book_pagination", property = "pagination"),
            @Result(column = "book_price", property = "price"),
            @Result(column = "book_uploadtime", property = "uploadTime"),
            @Result(column = "book_status", property = "status"),
            @Result(column = "book_borrower", property = "borrower"),
            @Result(column = "book_borrowtime", property = "borrowTime"),
            @Result(column = "book_returntime", property = "returnTime")
    })
    public List<Book> selectNewBook();

    // 根据图书的id查询图书信息
    @Select("select * from book where book_id=#{id}")
    @ResultMap("bookMap")
    public Book findById(int id);

    //首页借阅之后的保存
    public int editBook(Book book);
    //新增图书
    public int addBook(Book book);


    // 根据条件搜索图书（name/author/press），配合 PageHelper 分页
    @Select("<script>" +
            "select * from book where book_status!='3'" +
            "<if test='name != null and name != \"\"'> and book_name like concat('%', #{name}, '%')</if>" +
            "<if test='author != null and author != \"\"'> and book_author like concat('%', #{author}, '%')</if>" +
            "<if test='press != null and press != \"\"'> and book_press like concat('%', #{press}, '%')</if>" +
            " order by book_status asc, book_uploadtime desc" +
            "</script>")
    @ResultMap("bookMap")
    public java.util.List<Book> searchBooks(Book search);

    // 普通用户：查询自己未归还的图书（借阅中或归还中）
    @Select("<script>" +
            "select * from book where book_borrower=#{borrower}" +
            " and book_status in (1,2)" +
            "<if test='name != null and name != \"\"'> and book_name like concat('%',#{name},'%')</if>" +
            "<if test='author != null and author != \"\"'> and book_author like concat('%',#{author},'%')</if>" +
            "<if test='press != null and press != \"\"'> and book_press like concat('%',#{press},'%')</if>" +
            " order by book_borrowtime" +
            "</script>")
    @ResultMap("bookMap")
    public java.util.List<Book> selectMyBorrowed(Book search);

    // 管理员：查询自己借阅中的图书 + 所有待归还确认的图书
    @Select("<script>" +
            "select * from book where (book_borrower=#{borrower}" +
            " and book_status='1'" +
            "<if test='name != null and name != \"\"'> and book_name like concat('%',#{name},'%')</if>" +
            "<if test='author != null and author != \"\"'> and book_author like concat('%',#{author},'%')</if>" +
            "<if test='press != null and press != \"\"'> and book_press like concat('%',#{press},'%')</if>" +
            ") or (book_status='2'" +
            "<if test='name != null and name != \"\"'> and book_name like concat('%',#{name},'%')</if>" +
            "<if test='author != null and author != \"\"'> and book_author like concat('%',#{author},'%')</if>" +
            "<if test='press != null and press != \"\"'> and book_press like concat('%',#{press},'%')</if>" +
            ") order by book_borrowtime" +
            "</script>")
    @ResultMap("bookMap")
    public java.util.List<Book> selectBorrowed(Book search);

    // 管理员：查询所有超时未归还的图书（status=1 且 预计归还时间 < 当前日期）
    @Select("select * from book where book_status='1' and book_returntime < curdate() order by book_returntime")
    @ResultMap("bookMap")
    public java.util.List<Book> selectOverdueBooks();

    // 可预约图书（status=1或2）
    @Select("<script>" +
            "select * from book where book_status!='3' and book_status!='0'" +
            "<if test='name != null and name != \"\"'> and book_name like concat('%', #{name}, '%')</if>" +
            "<if test='author != null and author != \"\"'> and book_author like concat('%', #{author}, '%')</if>" +
            "<if test='press != null and press != \"\"'> and book_press like concat('%', #{press}, '%')</if>" +
            " order by book_status asc, book_uploadtime desc" +
            "</script>")
    @ResultMap("bookMap")
    public java.util.List<Book> selectReservableBooks(Book search);

    // 黑名单候选人：超时且被发送3次以上消息的借阅人（含黑名单状态）
    @Select("SELECT b.book_borrower AS borrower, COUNT(m.id) AS msgCount, " +
            "COALESCE((SELECT u.user_canBorrow FROM user u WHERE u.user_name = b.book_borrower), 0) AS canBorrow " +
            "FROM book b LEFT JOIN message m ON b.book_borrower = m.jieShouRen " +
            "WHERE b.book_status='1' AND b.book_returntime < CURDATE() " +
            "GROUP BY b.book_borrower HAVING COUNT(m.id) >= 3")
    public java.util.List<java.util.Map<String, Object>> selectBlacklistCandidates();
}
