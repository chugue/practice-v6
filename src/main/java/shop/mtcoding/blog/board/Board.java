package shop.mtcoding.blog.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    //@JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // db -> user_id

    @CreationTimestamp // pc -> db (날짜주입)
    private Timestamp createdAt;

    @Transient // 테이블 생성이 안됨
    private boolean isBoardOwner;


    //여기에 접근만 하면 lazyloading으로 조회 쿼리가 날라간다.
    @OrderBy("id desc")
    @OneToMany (mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt, boolean isBoardOwner) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
        this.isBoardOwner = isBoardOwner;
    }
}
