package com.zc.repository;

import com.zc.pojo.House;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author 小帅气
 * @create 2020-02-06-23:14
 */
@Repository
public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {
    @Query("select t from House t where t.houseTungId=:tungId and t.houseUnitId=:unitId and t.houseNumber=:number")
    House findByTungIdAndUnitIdAndNumber(@Param("tungId") int tungId, @Param("unitId") int unitId,
                                         @Param("number") int number);

    @Modifying
    @Query("update House h set h.houseMoney = h.houseMoney-h.houseArea*?1")
    int updateFee(BigDecimal unit);

    @Modifying
    @Query("update House h set h.houseState=2 where h.houseMoney<0")
    int autoUpdateState();

    @Modifying
    @Query("update House h set h.houseMoney=h.houseMoney+:money where h.houseId=:id")
    int payFee(@Param("id") Long id, @Param("money") BigDecimal money);

}
