package com.bendelivery.persistence;

import java.util.HashMap;
import java.util.List;

import com.bendelivery.domain.Criteria;
import com.bendelivery.domain.RestaurantVO;

public interface RestaurantDAO {
	public void register(RestaurantVO vo)throws Exception;
	public List<RestaurantVO> resList()throws Exception;
	public List<RestaurantVO> listByCategory(String category)throws Exception;
	public RestaurantVO read(int res_no)throws Exception;
	public RestaurantVO readByOwner(int owner_no)throws Exception;
	public void permit(RestaurantVO vo)throws Exception;
	public String getPermitInfo(int owner_no)throws Exception;
	public void modifyInfo(RestaurantVO vo)throws Exception;
	public List<RestaurantVO> listCri(Criteria cri)throws Exception;
	public int countPaging(Criteria cri)throws Exception;
	public int countPagingByCategory(String category)throws Exception;
	public List<RestaurantVO> listCriByCategory(HashMap<String, Object> map)throws Exception;
}
