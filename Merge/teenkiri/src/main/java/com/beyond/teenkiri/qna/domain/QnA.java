package com.beyond.teenkiri.qna.domain;

import com.beyond.teenkiri.comment.domain.Comment;
import com.beyond.teenkiri.common.domain.BaseTimeEntity;
import com.beyond.teenkiri.common.domain.DelYN;
import com.beyond.teenkiri.qna.dto.QnAAtoUpdateDto;
import com.beyond.teenkiri.qna.dto.QnAListResDto;
import com.beyond.teenkiri.qna.dto.QnAQtoUpdateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "qna")
public class QnA extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 3000, nullable = false)
    private String questionText;

    @Column(length = 3000)
    private String answerText;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private com.beyond.teenkiri.user_board.domain.user user; // 작성자

    @ManyToOne
    @JoinColumn(name = "answered_by")
    private com.beyond.teenkiri.user_board.domain.user answeredBy;

    @Column
    private LocalDateTime answeredAt;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private DelYN delYN = DelYN.N;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

//    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Report> reports;

    // 수정
    public QnAListResDto listFromEntity() {
        return QnAListResDto.builder()
                .id(this.id)
                .questionUserName(this.getUser().getNickname())
                .title(this.getTitle())
                .createdTime(this.getCreatedTime())
                .updatedTime(this.getUpdatedTime())
                .answeredAt(this.answeredAt)
                .answerText(this.answerText)
                .build();
    }

    public void QnAQUpdate(QnAQtoUpdateDto dto) {
        this.questionText = dto.getQuestionText();
        this.title = dto.getTitle();
        this.getUpdatedTime();
    }

    public void QnAAUpdate(QnAAtoUpdateDto dto) {
        this.answerText = dto.getAnswerText();
        this.answeredAt = LocalDateTime.now();
    }

    public void updateDelYN(DelYN delYN){
        this.delYN = delYN;
    }
}