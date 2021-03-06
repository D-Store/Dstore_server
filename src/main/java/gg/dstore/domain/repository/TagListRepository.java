package gg.dstore.domain.repository;

import gg.dstore.domain.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagListRepository extends PagingAndSortingRepository<TagEntity,Long> {
	Page<TagEntity> findByTagContaining(String tag, Pageable pageable);
}
