package gg.dstore.admin.domain.dto.banner.datalgnore;

import gg.dstore.domain.entity.BannerEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ASelectBanner {
    private Long id;
    private String fileLocation;

    public ASelectBanner(BannerEntity bannerEntity){
        this.id = bannerEntity.getId();
        this.fileLocation = bannerEntity.getFileLocation();
    }
}
