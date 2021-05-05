package gg.jominsubyungsin.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "project")
public class ProjectEntity {
	@Id
	@Column(name = "project_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private Date createAt = new Date();

	@ManyToMany(mappedBy = "projects")
	private List<UserEntity> users = new ArrayList<>();

	@OneToMany(mappedBy = "projectId")
	private List<FileEntity> files = new ArrayList<>();

	@OneToMany(mappedBy = "project")
	private List<LikeEntity> likes = new ArrayList<>();

	@OneToMany(mappedBy = "project")
	private List<CommentEntity> comments = new ArrayList<>();

	@ManyToMany(mappedBy = "projects")
	private List<TagEntity> tags = new ArrayList<>();


	public void add(UserEntity user) {
		users.add(user);
	}

	public void add(FileEntity fileEntity) {
		files.add(fileEntity);
		fileEntity.setProjectId(this);
	}

	public void add(LikeEntity like) {
		likes.add(like);
		like.setProject(this);
	}
	public void add(CommentEntity comment) {
		comments.add(comment);
		comment.setProject(this);
	}

	public void add(TagEntity tag){
		tags.add(tag);
	}

	@Builder
	public ProjectEntity(Long id, String title, String content, List<UserEntity> users, List<FileEntity> files, List<TagEntity> tags) {
		this.id = id;
		this.title = title;
		this.content = content;
		for (UserEntity user : users) {
			this.add(user);
			user.add(this);
		}
		for (FileEntity file : files) {
			this.add(file);
		}
		for(TagEntity tag: tags){
			this.add(tag);
			tag.add(this);
		}
	}
}
