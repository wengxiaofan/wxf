package com.oracle.gdms.web.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.GoodsType;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

@Path("/huazhao")
public class TestRest {
	@Path("/tuisong")
	@GET
	public void hello() {
		System.out.println("hello world");
	}
	//��һ�ִ��η���
//	@Path("/push/one/{goodsid}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public GoodsModel pushOne(@PathParam("goodsid") int goodsid) {
//		
//		GoodsService service = (GoodsService) Factory.getInstacance().getObject("goods.service.impl");
//		GoodsModel goods = service.findOne(goodsid);
//		return goods;
//	}
	//�ڶ��ִ��η���
	@Path("/push/one")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public GoodsModel pushOne(@QueryParam("goodsid") int goodsid) {
		
		GoodsService service = (GoodsService) Factory.getInstacance().getObject("goods.service.impl");
		GoodsModel goods = service.findOne(goodsid);
		return goods;
	}
	
	//�����ִ��η���
		@Path("/push/three")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		public GoodsModel pushThree(@FormParam("goodsid") int goodsid) {
			
			GoodsService service = (GoodsService) Factory.getInstacance().getObject("goods.service.impl");
			GoodsModel goods = service.findOne(goodsid);
			return goods;
		}
		//�����ִ��η���
				@Path("/push/four")
				@POST
				@Produces(MediaType.APPLICATION_JSON)
				@Consumes(MediaType.APPLICATION_JSON)  //����Ĳ�����json��ʽ
				public GoodsModel pushFour(GoodsEntity g) {
					
					GoodsService service = (GoodsService) Factory.getInstacance().getObject("goods.service.impl");
					GoodsModel goods = service.findOne(g.getGoodsid());
					return goods;
				}
		//�����ִ��η���
				@Path("/push/five")
				@POST
				@Produces(MediaType.APPLICATION_JSON)    //����Ĳ�����json��ʽ
				@Consumes(MediaType.APPLICATION_JSON)    //���صĽ����json��ʽ
				public GoodsModel pushFive(String param) {
					System.out.println("����Ĳ���");
					System.out.println(param);
					JSONObject j = JSONObject.parseObject(param);
				    System.out.println("id = " + j.getString("goodsid"));
				    System.out.println("name = " + j.getString("name"));
				    System.out.println("sex = " + j.getString("sex"));
					
//					System.out.println(map.get("goodsid"));
//					System.out.println(map.get("name"));
//					System.out.println(map.get("sex"));
				    GoodsService service = (GoodsService) Factory.getInstacance().getObject("goods.service.impl");
					GoodsModel goods = service.findOne(Integer.parseInt(j.getString("goodsid")));
					return goods;

				}
				@Path("/push/goods/type")
				@POST
				@Consumes(MediaType.APPLICATION_JSON)
				@Produces(MediaType.APPLICATION_JSON)
				public List<GoodsModel> pushGoodsByType(GoodsType type){
					List<GoodsModel> list = null;
					
					GoodsService service = (GoodsService) Factory.getInstacance().getObject("goods.service.impl");
					
					list = service.findByType(type.getGtid());
					
					System.out.println("size="+list.size() );
					return list;
				}
				@Path("/push/goods/one")
				@POST
				@Consumes(MediaType.APPLICATION_JSON)
				@Produces(MediaType.APPLICATION_JSON)
				public ResponseEntity pushGoods(String jsonstr){
					JSONObject j = JSONObject.parseObject(jsonstr);
					
					GoodsEntity goods = JSONObject.parseObject(j.getString("goods"),GoodsEntity.class);
					System.out.println("goodsid=" + goods.getGoodsid());
					System.out.println("name=" + goods.getName());
					System.out.println("area=" + goods.getArea());
					ResponseEntity rs = new ResponseEntity();
					rs.setCode(0);
					rs.setMessage("���ͳɹ�");
					rs.setData(null);
					return rs;
				}
				
}
