package com.teeny.wms.web.repository;

import com.teeny.wms.app.model.KeyValueEntity;
import com.teeny.wms.app.model.StringMapEntity;
import com.teeny.wms.web.model.dto.DocumentEntity;
import com.teeny.wms.web.model.response.DocumentResponseEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommonMapper {

    @Select("SELECT s.s_id AS id, s.name FROM ${account}.dbo.pda_storages s")
    @Results({@Result(column = "id", property = "key"),
            @Result(column = "name", property = "value")})
    List<KeyValueEntity> getWarehouseList(@Param("account") String account);

    @Select("SELECT db_name AS id, account_name AS name FROM pda_account_set")
    @Results({@Result(column = "id", property = "key"),
            @Result(column = "name", property = "value")})
    List<StringMapEntity> getAccountSets();

    List<KeyValueEntity> getAreaList(@Param("sId") int sId, @Param("account") String account);

    @Select("SELECT TOP 1 l.l_id FROM ${account}.dbo.pda_location l WHERE l.loc_code=#{locationCode}")
    Integer findLocationIdByCode(@Param("account") String account, @Param("locationCode") String locationCode);

    List<StringMapEntity> getHistoryLocation(@Param("account") String account, @Param("pId") int pId);

    List<DocumentEntity> getReceivingList(@Param("account") String account, @Param("sId") int sId);

    List<DocumentEntity> getAllotList(@Param("account") String account, @Param("sId") int sId);

    List<DocumentEntity> getPutawayList(@Param("account") String account, @Param("sId") int sId);

    List<DocumentEntity> getReviewList(@Param("account") String account, @Param("sId") int sId);

    @Select("SELECT TOP 1 l.l_id FROM ${account}.dbo.pda_location l WHERE l.loc_code=#{locationCode}")
    Integer getLocationIdByCode(@Param("locationCode") String locationCode,@Param("account") String account);
}
