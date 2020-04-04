package com.zc.business.impl;

import com.zc.business.HouseBusiness;
import com.zc.mapper.HouseMapper;
import com.zc.pojo.House;
import com.zc.pojo.PropertyPayment;
import com.zc.repository.HouseOwnershipRepository;
import com.zc.repository.HouseRepository;
import com.zc.repository.PropertyPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-06-23:14
 */
@Service
public class HouseBusinessImpl implements HouseBusiness {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HouseOwnershipRepository houseOwnershipRepository;
    @Resource
    private HouseMapper houseMapper;
    @Autowired
    private PropertyPaymentRepository propertyPaymentRepository;

    @Override
    public House findByTungIdAndUnitIdAndNumber(int tungId, int unitId, int number) {
        return houseRepository.findByTungIdAndUnitIdAndNumber(tungId, unitId, number);
    }

    /**
     * @param tungId    栋号
     * @param unitId    单元号
     * @param number    门牌号
     * @param maxArea   最大面积
     * @param minArea   最小面积
     * @param state     状态  1 正常 2欠物业费 3无业主
     * @param pageIndex 第几页 从1开始
     * @param pageSize  页大小
     * @return
     */
    @Override
    public Page<House> queryByPage(Integer tungId, Integer unitId, Integer number, BigDecimal maxArea,
                                   BigDecimal minArea, Integer state,
                                   Integer pageIndex, Integer pageSize) {
        return houseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if (tungId != null) {
                predicatesList.add(criteriaBuilder.equal(root.get("houseTungId").as(Integer.class), tungId));
            }
            if (unitId != null) {
                predicatesList.add(criteriaBuilder.equal(root.get("houseUnitId").as(Integer.class), unitId));
            }
            if (number != null) {
                predicatesList.add(criteriaBuilder.equal(root.get("houseNumber").as(Integer.class), number));
            }
            if (maxArea != null) {
                predicatesList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("houseArea").as(BigDecimal.class),
                        maxArea));
            }
            if (minArea != null) {
                predicatesList.add(criteriaBuilder.lessThanOrEqualTo(root.get("houseArea").as(BigDecimal.class),
                        minArea));
            }
            if (state != null) {
                predicatesList.add(criteriaBuilder.equal(root.get("houseState").as(Integer.class), state));
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, PageRequest.of(pageIndex - 1, pageSize));
    }

    @Override
    public int updateFee(BigDecimal unit) {
        return houseRepository.updateFee(unit);
    }

    @Override
    public int autoUpdateState() {
        return houseRepository.autoUpdateState();
    }

    /**
     * 缴费/扣费
     *
     * @param id
     * @param money 金额
     * @param type  0缴费   1扣费
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int payFee(Long id, BigDecimal money, Integer type) {
        if (type == 1) {
            money = money.negate();
        }
        return houseRepository.payFee(id, money);
    }

    @Override
    public int totalByUserId(Long userId) {
        return houseOwnershipRepository.queryCountByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> queryByUserId(Long userId, Integer pageIndex, Integer pageSize) {
        return houseMapper.queryHouseInfoByUserId(userId, (pageIndex - 1) * pageSize, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payManager(Long houseId, Long adminId, BigDecimal money) {
        House house = houseRepository.findById(houseId).get();
        house.setHouseMoney(house.getHouseMoney().add(money));
        PropertyPayment propertyPayment = new PropertyPayment();
        propertyPayment.setPropertyPaymentDatetime(new Date())
                .setPropertyPaymentHouseId(houseId)
                .setPropertyPaymentMoney(money)
                .setPropertyPaymentPayerId(adminId);
        propertyPaymentRepository.saveAndFlush(propertyPayment);
        houseRepository.saveAndFlush(house);
    }

    @Override
    public List<String> queryAddr(Long userId) {
        List<Map<String, Object>> mapList = houseMapper.queryHouse(userId);
        List<String> list = new ArrayList<>(mapList.size());
        for (Map<String, Object> map : mapList) {
            list.add(new StringBuilder().append(map.get("tungId")).append("幢").append(map.get("unitId")).append("单元").append(map.get("number")).toString());
        }
        return list;
    }
}
