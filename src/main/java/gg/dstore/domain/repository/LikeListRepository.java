package gg.dstore.domain.repository;

import gg.dstore.domain.entity.LikeEntity;
import gg.dstore.domain.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LikeListRepository extends PagingAndSortingRepository<LikeEntity, Long> {
	Page<LikeEntity> findByProjectAndState(ProjectEntity project, Boolean state, Pageable pageable);
}
