package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: post
 *
 */
@Entity(name="post")
public class Post implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String content;
	private String url;
	@Enumerated(EnumType.STRING)
	private Post_t type;
	private Date date;
	private int like;
	private int dislike;
    @OneToMany(targetEntity=Comment.class,cascade=CascadeType.ALL)
	private List<Comment>comments;
    @ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	private static final long serialVersionUID = 1L;

	public Post() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public int getLike() {
		return this.like;
	}

	public void setLike(int like) {
		this.like = like;
	}   
	public int getDislike() {
		return this.dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Post_t getType() {
		return type;
	}
	public void setType(Post_t type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

   
}
