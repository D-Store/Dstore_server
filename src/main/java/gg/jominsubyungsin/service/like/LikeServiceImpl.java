package gg.jominsubyungsin.service.like;

import gg.jominsubyungsin.domain.dto.like.dataIgnore.SelectLikeDto;
import gg.jominsubyungsin.domain.entity.LikeEntity;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import gg.jominsubyungsin.domain.entity.UserEntity;
import gg.jominsubyungsin.domain.repository.LikeListRepository;
import gg.jominsubyungsin.domain.repository.LikeRepository;
import gg.jominsubyungsin.domain.repository.ProjectRepository;
import gg.jominsubyungsin.service.follow.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService{
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	LikeListRepository likeListRepository;
	@Autowired
	LikeRepository likeRepository;
	@Autowired
	FollowService followService;
	@Override
	public List<SelectLikeDto> getUserList(Long id, Pageable pageable,UserEntity me) {

		try {
			List<SelectLikeDto> likes = new ArrayList<>();
			ProjectEntity project = projectRepository.findById(id).orElseGet(() -> {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 프로젝트");
			});
			Page<LikeEntity> likeList = likeListRepository.findByProjectAndState(project, true, pageable);


			for (LikeEntity likeEntity : likeList) {
				Boolean follow = followService.followState(likeEntity.getUser(), me);
				likes.add(new SelectLikeDto(likeEntity, follow));
			}
			return likes;
		}catch (Exception e){
			e.printStackTrace();
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러");
		}
	}

	/*
	 * 게시글 좋아요
	 */
	@Override
	public void changeLikeState(Long id, UserEntity user) {
		try{
			ProjectEntity project = projectRepository.findById(id).orElseGet(()->{
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"존재하지 않는 게시글");
			});

			LikeEntity likeEntity = likeRepository.findByProjectAndUser(project, user)
					.orElse(new LikeEntity(project,user,false));
			likeEntity.setState(!likeEntity.getState());
			project.add(likeEntity);

			likeRepository.save(likeEntity);
		}catch (Exception e) {
			e.printStackTrace();
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
		}
	}

	@Override
	public Long LikeNum(Long id) {
		try{
			ProjectEntity project = projectRepository.findById(id).orElseGet(()->{
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"존재하지 않는 게시글");
			});

			return  likeRepository.countByProjectAndState(project,true);
		}catch (Exception e){
			e.printStackTrace();
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러");
		}
	}
}