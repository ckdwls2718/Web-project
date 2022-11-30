package com.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.CartMapper;
import com.shop.mapper.ProductMapper;
import com.shop.model.CartVO;
import com.shop.model.ProductVO;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<ProductVO> selectByPspec(String pspec) {
		
		return productMapper.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(int cg_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO selectByPnum(int pnum) {
		
		return productMapper.selectByPnum(pnum);
	}

	@Override
	public int addCart(CartVO cartVo) {
		// 상품번호와 회원번호로 cart 테이블에 있는 상품개수 가져오기
		Integer cnt = cartMapper.selectCartCountByPnum(cartVo);
		
		if(cnt != null) {
			// [1] 장바구니에 추가하는 상품이 이미 장바구니에 담겨 있는 경우라면 수량만 수정 =>update
			int n = cartMapper.updateCartQty(cartVo);
			return n;
		} else {
			// [2] 장바구니에 없는 상품을 담은 경우라면 => insert
			int n = cartMapper.addCart(cartVo);
			return n;
		}
		
	}

	@Override
	public int updateCartQty(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editCart(CartVO cartVo) {
		
		int qty=cartVo.getOqty();
		if(qty==0) {
			return cartMapper.delCart(cartVo.getCartNum());
		} else if(qty<0) {
			throw new NumberFormatException("수량은 음수로 입력하면 안됩니다.");
		} else if(qty>50){
			throw new NumberFormatException("수량은 50개 이상 입력하면 안됩니다.");
		} else {
			return cartMapper.editCart(cartVo);
		}
	}

	@Override
	public List<CartVO> selectCartView(int midx) {
		return cartMapper.selectCartView(midx);
	}

	@Override
	public int delCart(int cartNum) {
		return cartMapper.delCart(cartNum);
	}

	@Override
	public int delCartAll(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCartOrder(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCartCountByIdx(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CartVO getCartTotal(int midx_fk) {
		return cartMapper.getCartTotal(midx_fk);
	}

	@Override
	public void delCartByOrder(int midx_fk, int pnum) {
		// TODO Auto-generated method stub
		
	}
	
}
