package com.jeesite.modules.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.entity.DashangOrder;
import com.jeesite.modules.app.entity.DoctorInfo;
import com.jeesite.modules.app.entity.DoctorRegisterOrder;
import com.jeesite.modules.app.entity.MemberOrder;
import com.jeesite.modules.app.entity.Order;
import com.jeesite.modules.app.entity.OrderDetail;
import com.jeesite.modules.app.entity.TiaoliOrder;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.service.AddressService;
import com.jeesite.modules.app.service.AirDrugCommentSercvie;
import com.jeesite.modules.app.service.BasketService;
import com.jeesite.modules.app.service.DashangOrderService;
import com.jeesite.modules.app.service.DoctorInfoService;
import com.jeesite.modules.app.service.DoctorRegisterOrderService;
import com.jeesite.modules.app.service.MemberOrderService;
import com.jeesite.modules.app.service.OrderDetailService;
import com.jeesite.modules.app.service.OrderService;
import com.jeesite.modules.app.service.TiaoliOrderService;
import com.jeesite.modules.app.service.UserInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.RandomUtil;
import com.jeesite.modules.app.utils.Result;

@Controller
@SuppressWarnings("rawtypes")
@CrossOrigin
@RequestMapping(value = "${frontPath}/sys/basketController")
/**
 * 
 * @author 范耘诚
 *
 */
public class BasketController {

	private static final String String = null;
	@Autowired
	private BasketService basketService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private TiaoliOrderService tiaoliOrderService;
	
	@Autowired
	private MemberOrderService memberOrderService;
	@Autowired
	private  DoctorRegisterOrderService doctorRegisterOrderService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private DoctorInfoService doctorInfoService;
	/**
	 ** token token 随便 userId 用户id 123 展示当前用户的购物车列表
	 * /js/f/sys/basketController/showUserBasket
	 */
	@ResponseBody
	@RequestMapping(value = "/showUserBasket")
	public Result showUserBasket(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			// 获取token------start
			String token = (String) requestParams.get("token");
			// 获取token------end
			String userId = (String) requestParams.get("userId");
			// 通过userId得到用户的购物车列表
			Map userMap = new HashMap<>();
			userMap.put("userId", userId);
			List<Map> basketInfoList = basketService.getBasketByUserId(userMap);
			JSONObject result = new JSONObject();

			result.put("basketInfoList", basketInfoList);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}

	}

	/**
	 ** token token 随便 userId 用户id 123 drugId 药品id 1 drugCount 加入购物车的药品数量 1
	 * 中药产品加入购物车的操作 /js/f/sys/basketController/addDrugBasket
	 */
	@ResponseBody
	@RequestMapping(value = "/addDrugBasket")
	public Result addDrugBasket(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		// 获取token------start
		try {
			String token = (String) requestParams.get("token");
			// 获取token------end
			String userId = (String) requestParams.get("userId");
			// 获取drugId
			String drugId = (String) requestParams.get("drugId");
			String drugCount = (String) requestParams.get("drugCount");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("drugId", drugId);
			parmMap.put("drugCount", drugCount);
			// 中药产品加入购物车的操作
			basketService.addDrugBasket(parmMap);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.success(CodeMsg.PARAMETER_ISNULL);
		}

	}

	/**
	 ** token token 随便 userId 用户id 123 drugId 药品id 1 drugCount 加入购物车的药品数量 1
	 * 删除购物车商品的操作 /js/f/sys/basketController/delFlagDrugBasketByDrugId
	 */
	@ResponseBody
	@RequestMapping(value = "/delFlagDrugBasketByDrugId")
	public Result delFlagDrugBasketByDrugId(HttpServletRequest request,
			@RequestBody Map<String, Object> requestParams) {
		// 获取token------start
		try {
			String token = (String) requestParams.get("token");
			// 获取token------end
			String userId = (String) requestParams.get("userId");
			// 获取drugId
			String drugId = (String) requestParams.get("drugId");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("drugId", drugId);
			// 删除购物车商品的操作
			basketService.delFlagDrugBasketByDrugId(parmMap);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.success(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 ** token token 随便 * userId 用户id 123 * realName 联系人 * phone 电话 * province 省份 *
	 * city 城市 * area 地区 * street 详细信息 * isDefault 是否设为默认 0否 1是 * addressId
	 * 修改地址时出现的地址id
	 * 
	 * 添加地址 /js/f/sys/basketController/addAddress
	 */
	@ResponseBody
	@RequestMapping(value = "/addAddress")
	public Result addAddress(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String realName = (String) requestParams.get("realName");
			String phone = (String) requestParams.get("phone");
			String province = (String) requestParams.get("province");
			String city = (String) requestParams.get("city");
			String area = (String) requestParams.get("area");
			String street = (String) requestParams.get("street");
			String isDefault = (String) requestParams.get("isDefault");
			String userId = (String) requestParams.get("userId");
			String addressId = (String) requestParams.get("addressId");
			Map parmMap = new HashMap<>();
			parmMap.put("realName", realName);
			parmMap.put("province", province);
			parmMap.put("city", city);
			parmMap.put("area", area);
			parmMap.put("street", street);
			parmMap.put("isDefault", isDefault);
			parmMap.put("phone", phone);
			parmMap.put("createDate", new Date());
			parmMap.put("userId", userId);
			if (StringUtils.isEmpty(addressId)) {
				// 增加地址
				addressService.insertAddress(parmMap);
			} else {
				parmMap.put("addressId", addressId);
				// 修改地址
				addressService.updateAddress(parmMap);
			}
			if (isDefault.trim().equals("1")) {
				addressService.modifyAddressIsDefault(parmMap);
			}
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.success(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 ** token token 随便 userId 用户id 123 查看此用户下的所有地址
	 * /js/f/sys/basketController/showAddressByUser
	 */
	@ResponseBody
	@RequestMapping(value = "/showAddressByUser")
	public Result showAddressByUser(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			Map parmMap = new HashMap<>();
			parmMap.put("token", token);
			parmMap.put("userId", userId);
			// 查看此用户下的所有地址
			List<Map> addressList = addressService.showAddressByUserId(parmMap);
			JSONObject result = new JSONObject();
			result.put("addressList", addressList);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 ** token token 随便 userId 用户id 123 addressId 要修改的默认地址的id 修改默认地址
	 * /js/f/sys/basketController/modifyAddressIsDefault
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyAddressIsDefault")
	public Result modifyAddressIsDefault(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String addressId = (String) requestParams.get("addressId");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("addressId", addressId);
			// 修改默认地址
			addressService.modifyAddressIsDefault(parmMap);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}

	}

	/**
	 ** token token 随便 userId 用户id 123 addressId 要修改的默认地址的id 删除默认地址
	 * /js/f/sys/basketController/delAddress
	 */
	@ResponseBody
	@RequestMapping(value = "/delAddress")
	public Result delAddress(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String addressId = (String) requestParams.get("addressId");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("addressId", addressId);
			// 删除地址
			addressService.delAddress(parmMap);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 ** token token 随便 userId 用户id 123 addressId 要查询的默认地址的id 通过主键查询接口
	 * /js/f/sys/basketController/showAddressByPrimary
	 */
	@ResponseBody
	@RequestMapping(value = "/showAddressByPrimary")
	public Result showAddressByPrimary(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String addressId = (String) requestParams.get("addressId");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("addressId", addressId);
			Map addressMap = addressService.showAddressByPrimary(parmMap);
			JSONObject result = new JSONObject();
			result.put("addressMap", addressMap);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 ** token token 随便 userId 用户id 123 drudIds 选中的要查询的 drudIds药品Id们 用,号分割
	 * prescriptionIds 选中的要查询的药方Id们用,号分割 (暂时不用) addressId 地址id 非必填 选中药品后进入提交订单页面
	 * /js/f/sys/basketController/showPrepareOrder
	 */
	@ResponseBody
	@RequestMapping(value = "/showPrepareOrder")
	public Result showPrepareOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String drudIds = (String) requestParams.get("drudIds");
			String prescriptionIds = (String) requestParams.get("prescriptionIds");
			String addressId = (String) requestParams.get("addressId");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			if (!StringUtils.isEmpty(drudIds)) {
				parmMap.put("drudIds", drudIds);
			}
			if (!StringUtils.isEmpty(prescriptionIds)) {
				parmMap.put("prescriptionIds", prescriptionIds);
			}
			if (!StringUtils.isEmpty(addressId)) {
				parmMap.put("addressId", addressId);
			}
			// 得到此用户的默认收货地址
			Map addressMap = addressService.showAddressDefaultByUserId(parmMap);
			// 得到选中的商品和药方列表
			List<Map> productMapList = addressService.showPitchProduct(parmMap);
			// 得到商品的总价
			Integer productTotalPrice = 0;
			for (int i = 0; i < productMapList.size(); i++) {
				Double typeTotalPrice = (Double) productMapList.get(i).get("type_total_price");
				productTotalPrice += (typeTotalPrice.intValue());
			}
			parmMap.put("productTotalPrice", productTotalPrice);

			// 红包的计算的操作
			Integer discountArithmeticPrice = addressService.showDiscountArithmetic(parmMap, productTotalPrice);
			// 应付金额
			Integer buyPrice = productTotalPrice - discountArithmeticPrice;

			JSONObject result = new JSONObject();
			// 收货地址
			result.put("addressMap", addressMap);
			// 得到选中的商品和药方列表
			result.put("productMapList", productMapList);
			// 得到商品的总价
			result.put("productTotalPrice", productTotalPrice);
			// 红包的计算的操作
			result.put("discountArithmeticPrice", discountArithmeticPrice);
			// 应付金额
			result.put("buyPrice", buyPrice);
			return Result.success(result);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 * ** token token 随便 post userId 用户id 123 drudIds 提交订单的 drudIds药品Id们 用,号分割
	 * 重复的重复显示 1,2,3,1
	 * 
	 * prescriptionIds 提交订单的 选中的要查询的药方Id们用,号分割 重复的重复显示 1,1,1,2,2,3,4 (暂时不用)
	 * addressId 地址id 非必填 buyPrice 付款总价格 discountArithmeticPrice 折扣价格
	 * productTotalPrice 商品没打折时候的总价格 shoppingcart 是否是从购物车跳转的 用户点击提交订单后所做的操作
	 * /js/f/sys/basketController/submitOrder
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/alwaysSubmitOrder", method = RequestMethod.POST)
	public Result alwaysSubmitOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String orderId = (String) requestParams.get("orderId");
			Order order =new Order();
			order.setId(orderId);
			order=orderService.get(order);
			return Result.success(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * ** token token 随便 post userId 用户id 123 drudIds 提交订单的 drudIds药品Id们 用,号分割
	 * 重复的重复显示 1,2,3,1
	 * 
	 * prescriptionIds 提交订单的 选中的要查询的药方Id们用,号分割 重复的重复显示 1,1,1,2,2,3,4 (暂时不用)
	 * addressId 地址id 非必填 buyPrice 付款总价格 discountArithmeticPrice 折扣价格
	 * productTotalPrice 商品没打折时候的总价格 shoppingcart 是否是从购物车跳转的 用户点击提交订单后所做的操作
	 * /js/f/sys/basketController/submitOrder
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/submitTiaoLiOrder", method = RequestMethod.POST)
	public Result submitTiaoLiOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String docid = (String) requestParams.get("docid");
			String comecost = (String) requestParams.get("comecost");
			String count = (String) requestParams.get("count");
			String totalcost = (String) requestParams.get("totalcost");
			
			TiaoliOrder tiaoliOrder =new TiaoliOrder();
			tiaoliOrder.setUserid(userId);
			tiaoliOrder.setDocid(docid);
			tiaoliOrder.setComeCost(comecost);
			tiaoliOrder.setCount(count);
			tiaoliOrder.setTotalPrice(totalcost);
			
			tiaoliOrder.setOrderNo(RandomUtil.getOrderCode());
			tiaoliOrder.setOrderStatus("0");
			tiaoliOrder.setCreateDate(new Date());
			tiaoliOrderService.insert(tiaoliOrder);

			return Result.success(tiaoliOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/submitDoctorRegisterOrder", method = RequestMethod.POST)
	public Result submitDoctorRegisterOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String docid = (String) requestParams.get("docid");
			DoctorRegisterOrder doctorRegisterOrder =new DoctorRegisterOrder();
			doctorRegisterOrder.setOrderNo(RandomUtil.getOrderCode());
			doctorRegisterOrder.setOrderStatus("0");
			doctorRegisterOrder.setUserid(userId);
			doctorRegisterOrder.setDoctorUserId(docid);
			Map<String,Object> doctorInfoMap=  doctorInfoService.getDoctorInfoMapByDoctorId(docid);
			String registerPrice=doctorInfoMap.get("register_price").toString();
			doctorRegisterOrder.setTotalPrice(registerPrice);
			doctorRegisterOrder.setCreateDate(new Date());
			doctorRegisterOrderService.insert(doctorRegisterOrder);
			return Result.success(doctorRegisterOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	@Autowired
	private  DashangOrderService  dashangOrderService;
	@ResponseBody
	@RequestMapping(value = "/submitDashangOrder", method = RequestMethod.POST)
	public Result submitDashangOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String docid = (String) requestParams.get("docid");
			String totalPrice = (String) requestParams.get("totalPrice");
			DashangOrder dashangOrder =new DashangOrder();
			dashangOrder.setOrderNo(RandomUtil.getOrderCode());
			dashangOrder.setOrderStatus("0");
			dashangOrder.setUserid(userId);
			dashangOrder.setDocid(docid);
			dashangOrder.setTotalPrice(totalPrice);
			dashangOrder.setCreateDate(new Date());
			dashangOrderService.insert(dashangOrder);
			return Result.success(dashangOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 * ** token token 随便 post userId 用户id 123 drudIds 提交订单的 drudIds药品Id们 用,号分割
	 * 重复的重复显示 1,2,3,1
	 * 
	 * prescriptionIds 提交订单的 选中的要查询的药方Id们用,号分割 重复的重复显示 1,1,1,2,2,3,4 (暂时不用)
	 * addressId 地址id 非必填 buyPrice 付款总价格 discountArithmeticPrice 折扣价格
	 * productTotalPrice 商品没打折时候的总价格 shoppingcart 是否是从购物车跳转的 用户点击提交订单后所做的操作
	 * /js/f/sys/basketController/submitOrder
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/submitMemberOrder", method = RequestMethod.POST)
	public Result submitMemberOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String totalPrice = (String) requestParams.get("totalPrice");
			MemberOrder memberOrder =new MemberOrder();
			memberOrder.setUserid(userId);
			memberOrder.setTotalPrice(totalPrice);
			memberOrder.setOrderNo(RandomUtil.getOrderCode());
			memberOrder.setOrderStatus("0");
			memberOrder.setCreateDate(new Date());
			memberOrderService.insert(memberOrder);
			return Result.success(memberOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
	public Result submitOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String drudIds = (String) requestParams.get("drudIds");
			String prescriptionIds = (String) requestParams.get("prescriptionIds");
			String addressId = (String) requestParams.get("addressId");
			String shoppingcart = (String) requestParams.get("shoppingcart");
			// 删除当前购物车所属药品id的操作
			if (shoppingcart != null && shoppingcart.trim().equals("true")) {
				String[] str = drudIds.split(",");
				Set<String> set = new HashSet();
				// 遍历数组并存入集合,如果元素已存在则不会重复存入
				for (int i = 0; i < str.length; i++) {
					set.add(str[i]);
				}
				for (String drudIdStr : set) {
					Map<String, Object> parmMap = new HashMap<>();
					parmMap.put("userId", userId);
					parmMap.put("drugId", drudIdStr);
					basketService.delFlagDrugBasketByDrugId(parmMap);
				}
			}
			// 删除结束
			// 传过来的折扣价
			String discountArithmeticPrice = (String) requestParams.get("discountArithmeticPrice");
			// 商品没打折时候的总价格
			String productTotalPrice = (String) requestParams.get("productTotalPrice");
			// 付款总价
			String buyPrice = (String) requestParams.get("buyPrice");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			if (!StringUtils.isEmpty(drudIds)) {
				parmMap.put("drudIds", drudIds);
			}
			if (!StringUtils.isEmpty(prescriptionIds)) {
				parmMap.put("prescriptionIds", prescriptionIds);
			}
			if (!StringUtils.isEmpty(addressId)) {
				parmMap.put("addressId", addressId);
			}
			// 得到地址
			Map addressMap = addressService.showAddressDefaultByUserId(parmMap);
			// 商品和药方列表
			List<Map> productMapList = addressService.showPitchProduct(parmMap);
			// 得到商品的总价
			Integer selectProductTotalPrice = 0;
			//
			// 商品总个数
			Integer totalCount = 0;
			for (int i = 0; i < productMapList.size(); i++) {
				// 类型总价格
				Double typeTotalPrice = (Double) productMapList.get(i).get("type_total_price");
				selectProductTotalPrice += (typeTotalPrice.intValue());
				// 商品个数
				Long typeCount = (Long) productMapList.get(i).get("type_count");
				totalCount += (typeCount.intValue());
			}
			// 红包的计算的操作
			Integer selectDiscountArithmeticPrice = addressService.showDiscountArithmetic(parmMap,
					selectProductTotalPrice);
			// 应付金额
			Integer selectBuyPrice = selectProductTotalPrice - selectDiscountArithmeticPrice;
			// 参数错误
			if (Integer.parseInt(buyPrice) != selectBuyPrice) {
				return Result.error(CodeMsg.PRICE_FAIL);
			} else {
				// 主表主要的信息----------start---------------
				// 订单编号(本地自动生成)
				String orderNo = RandomUtil.getOrderCode();
				// 未付款
				String orderStatus = "0";
				// 商品类别数量
				/* product_count */
				String productCount = ((Integer) productMapList.size()).toString();
				// 不打折总价
				String totalPrice = productTotalPrice;
				// 运费金额
				String logisticsPrice = "0";
				// 折扣价格
				String discountPrice = selectDiscountArithmeticPrice.toString();
				// 真实支付价格
				String reallyPrice = ((Integer) (selectBuyPrice + new Integer(logisticsPrice))).toString();
				// 支付类型
				String payChannel = "null";
				// 第三方支付编号
				String outTradeNo = "";
				// 创建时间
				Date createDate = new Date();
				// userId
				String userIds = userId;
				String deliveryName = (String) addressMap.get("real_name");
				String deliveryPhone = (String) addressMap.get("phone");
				String deliveryAddress = (String) addressMap.get("province") + (String) addressMap.get("area")
						+ (String) addressMap.get("street");
				Map<String, Object> orderParmMap = new HashMap<>();
				orderParmMap.put("orderNo", orderNo);
				orderParmMap.put("orderStatus", orderStatus);
				orderParmMap.put("productCount", productCount);
				orderParmMap.put("totalPrice", totalPrice);
				orderParmMap.put("logisticsPrice", logisticsPrice);
				orderParmMap.put("discountPrice", discountPrice);
				orderParmMap.put("reallyPrice", reallyPrice);
				orderParmMap.put("payChannel", payChannel);
				orderParmMap.put("outTradeNo", outTradeNo);
				orderParmMap.put("createDate", createDate);
				orderParmMap.put("userId", userIds);
				orderParmMap.put("deliveryName", deliveryName);
				orderParmMap.put("deliveryAddress", deliveryAddress);
				orderParmMap.put("deliveryPhone", deliveryPhone);
				orderParmMap.put("totalCount", totalCount);
				// 插入订单并返回Id
				Integer orderId = orderService.insertOrderReturnId(orderParmMap);
				// 主表主要的信息----------end---------------
				// 明细表主要的信息 --------start------------
				List orderDetailMapList = new ArrayList<>();
				for (int i = 0; i < productMapList.size(); i++) {
					Map orderDetailMap = new HashMap<>();
					// 订单编号
					String orderCodeDetail = orderNo;
					// 商品id
					String grudIdDetail = String.valueOf((productMapList.get(i).get("id")));
					// 商品名称
					String productNameDetail = (String) productMapList.get(i).get("name");
					// 单个价格
					String onlyPriceDetail = (String) productMapList.get(i).get("pay_price");
					// 商品数量
					String buyCountDetail = String.valueOf(((Long) productMapList.get(i).get("type_count")));
					// 商品小计价格
					String subtotalPriceDetail = String
							.valueOf(((Double) productMapList.get(i).get("type_total_price")).intValue());
					// 是否失效
					String invalidFlagDetail = "0";
					// 创建时间
					Date createDateDetail = new Date();
					String orderIdDetail = String.valueOf(orderId);
					orderDetailMap.put("orderCode", orderCodeDetail);
					orderDetailMap.put("grudId", grudIdDetail);
					orderDetailMap.put("productName", productNameDetail);
					orderDetailMap.put("onlyPrice", onlyPriceDetail);
					orderDetailMap.put("buyCount", buyCountDetail);
					orderDetailMap.put("subtotalPrice", subtotalPriceDetail);
					orderDetailMap.put("invalidFlag", invalidFlagDetail);
					orderDetailMap.put("orderId", orderIdDetail);
					orderDetailMap.put("createDate", createDateDetail);
					orderDetailMapList.add(orderDetailMap);
				}
				// 批量增加订单吗详情表
				orderDetailService.insertOrderDetailAll(orderDetailMapList);
				// 明细表主要的信息 --------end--------------

				// 调用支付接口

				return Result.success(orderParmMap);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 * 查看我的订单 /js/f/sys/basketController/showMyOrderList ** token token 随便 post
	 * userId 用户id 123 orderStatus 订单状态 ""或者0 1 2 3 pageNum 第几页 pageSize 每页显示多少内容
	 */
	@ResponseBody
	@RequestMapping(value = "/showMyOrderList", method = RequestMethod.POST)
	public Result ShowMyOrderList(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");

			// 订单状态
			String orderStatus = (String) requestParams.get("orderStatus");
			String pageNum = (String) requestParams.get("pageNum");
			String pageSize = (String) requestParams.get("pageSize");
			Map parmMap = new HashMap<>();
			if (!StringUtils.isEmpty(orderStatus)) {
				parmMap.put("orderStatus", orderStatus);
			}
			parmMap.put("userId", userId);
			PageModel pageModel = new PageModel(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
			parmMap.put("pageModel", pageModel);
			// 通过userId和订单状态查找集合
			List<Map> orderList = orderService.findOrderInfoByOrderStatusAndUserId(parmMap);
			JSONObject result = new JSONObject();
			// 订单信息集合
			result.put("orderList", orderList);

			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 * 订单详情 ** token token 随便 post userId 用户id 123 orderId 订单id
	 * /js/f/sys/basketController/orderDetailsInfo
	 */
	@ResponseBody
	@RequestMapping(value = "/orderDetailsInfo", method = RequestMethod.POST)
	public Result orderDetailsInfo(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String orderId = (String) requestParams.get("orderId");
			// 通过主键查询
			Order order = new Order();
			order.setId(orderId);
			order.setUserId(userId);
			order = orderService.get(order);
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(new Long(orderId));
			List<OrderDetail> orderDetailList = orderDetailService.findList(orderDetail);
			JSONObject result = new JSONObject();
			result.put("order", order);
			result.put("orderDetailList", orderDetailList);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 * 软删除订单 ** token token 随便 post userId 用户id 123 orderId 订单id
	 * /js/f/sys/basketController/delFlagOrder
	 */
	@ResponseBody
	@RequestMapping(value = "/delFlagOrder", method = RequestMethod.POST)
	public Result delFlagOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String orderId = (String) requestParams.get("orderId");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("orderId", orderId);

			/** 软删除订单 */
			orderService.updateDelFlagOrderByOrderId(parmMap);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 * 订单支付页成功的假接口 ** token token 随便 post userId 用户id 123 orderId 订单id
	 * /js/f/sys/basketController/buySuccessOrder
	 */
	@ResponseBody
	@RequestMapping(value = "/buySuccessOrder", method = RequestMethod.POST)
	public Result buySuccessOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String orderId = (String) requestParams.get("orderId");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("orderId", orderId);
			/** 订单支付页成功假 */
			orderService.buySuccessOrder(parmMap);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 * 确认收货接口 ** token token 随便 post userId 用户id 123 orderId 订单id
	 * /js/f/sys/basketController/buyConfirm
	 */
	@ResponseBody
	@RequestMapping(value = "/buyConfirm", method = RequestMethod.POST)
	public Result buyConfirm(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String orderId = (String) requestParams.get("orderId");
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("orderId", orderId);
			parmMap.put("orderStatus", "3");
			/** 通过id修改订单状态 */
			orderService.changeOrderStatusByOrderId(parmMap);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	@Autowired
	private AirDrugCommentSercvie airDrugCommentSercvie;

	// 订单商品评价
	@ResponseBody
	@RequestMapping(value = "/evaluateOrder", method = RequestMethod.POST)
	public Result evaluateOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		/*
		 * "token":token, "userId":userId, "orderId":orderId, "content":content,
		 * "startnum":startnum
		 */
		try {
			String token = (String) requestParams.get("token");
			String userId = (String) requestParams.get("userId");
			String orderId = (String) requestParams.get("orderId");
			String content = (String) requestParams.get("content");
			String startnum = ((Integer) requestParams.get("startnum")).toString();
			Map parmMap = new HashMap<>();
			parmMap.put("userId", userId);
			parmMap.put("orderId", orderId);
			parmMap.put("content", content);
			parmMap.put("startnum", startnum);
			/** 通过订单id集体评价药品 */
			airDrugCommentSercvie.insertCommentByOrderId(parmMap);
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}

	}

}
