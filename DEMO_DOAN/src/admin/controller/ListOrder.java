package admin.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.bcel.internal.generic.NEW;

import poly.entity.Members;
import poly.entity.Tuyens;

@Transactional
@Controller
@RequestMapping("/admin/")
public class ListOrder {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value ="{id}/list-order")
	public String admin(ModelMap md,@PathVariable("id") int id) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		Session session = factory.getCurrentSession();
		String hql ="from Tuyens";
		Query query = session.createQuery(hql);
		List<Tuyens> ds = query.list();
		if(ds.size() == 0) {
			md.addAttribute("message","Không có tuyến xe nào hết");
			return "admin/list-order-erorr";
		}
		else
		{
			md.addAttribute("ds", ds);
			return "admin/list-order";
		}
	
	}
	@RequestMapping(value = "{id}/list-order/delete/{id1}", params = "lnkDelete")
	public String delete(ModelMap md, @PathVariable("id") int id,@PathVariable("id1") int id1) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		Tuyens s =new Tuyens();
		s.setMaTuyen(id1);
		Session  session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(s);
			t.commit();
			md.addAttribute("message","Xóa tuyến thành công");
		}
		catch(Exception ex) {
			t.rollback();
			md.addAttribute("message","Xóa tuyến thất bại !. Tuyến đã có người đặt");
		}
		finally {
			session.close();
		}
		return "admin/home";
	}
	@RequestMapping(value = "{id}/list-order/insert", method = RequestMethod.GET)
	public String getInsert(ModelMap md, @PathVariable("id") int id) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten()); 
		md.addAttribute("image", ds1.get(0).getImage());
		
		md.addAttribute("tuyen", new Tuyens());
		return "admin/order/insert-order";
	}
	@RequestMapping(value ="{id}/list-order/insert", method = RequestMethod.POST)
	public String Insert(ModelMap md, @ModelAttribute("tuyen") Tuyens tuyen, BindingResult err,@PathVariable("id") int id) 
	{
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		if(tuyen.getTenXe().trim().length() == 0 ) {
			err.rejectValue("tenXe","tuyen","Tên xe không được bỏ trống");
		}
		if(tuyen.getDiemDi().trim().length() == 0) {
			err.rejectValue("diemDi","tuyen","Điểm đi không được bỏ trống");
		}
		if(tuyen.getDiemDen().trim().length() == 0) {
			err.rejectValue("diemDen","tuyen","Điểm đến không được bỏ trống");
		}
		if(tuyen.getThoiGian() ==  null) {
			err.rejectValue("thoiGian", "tuyen", "Vui lòng chọn ngày");
		}
		else {
				Date date = new Date();
				if(tuyen.getThoiGian().compareTo(date) <= 0) {
					err.rejectValue("thoiGian", "tuyen", "Ngày không hợp lệ. Vui lòng chọn sau hôm nay");
				}
		}
		
		if(tuyen.getSoVe() <= 0) {
			err.rejectValue("soVe", "tuyen", "Số lượng vé không phải lớn hơn 0");
		}
		
		if(tuyen.getGiaVe() <= 0) {
			err.rejectValue("giaVe", "tuyen", "Gía vé phải lớn hơn 0");
		}
		if(err.hasErrors()) {
			md.addAttribute("message", "Vui lòng sửa lỗi sau");
		}
		else {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(tuyen);
				t.commit();
				md.addAttribute("message", "Thêm tuyến thành công");
			}catch(Exception ex){
				t.rollback();
				md.addAttribute("message", "Thêm tuyến thất bại");
			
			}finally {
				session.close();
			}
		}
		return "admin/order/insert-order";
	}
	@RequestMapping(value = "{id}/list-order/update/{id1}", params = "lnkUpdate")
	public String getUpdate(ModelMap md, @PathVariable ("id") int id, @PathVariable("id1") int id1) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		Session session = factory.getCurrentSession();
		String hql = "from Tuyens s where s.maTuyen=:id1";
		Query query = session.createQuery(hql);
		query.setParameter("id1", id1);
		List<Tuyens>  ds = query.list();
		Tuyens s = ds.get(0);
		md.addAttribute("tuyen",s);
		return "admin/order/update-order";
	}
	@RequestMapping(value = "{id}/list-order/update", method = RequestMethod.POST)
	public String update(ModelMap md, @ModelAttribute("tuyen") Tuyens tuyen,BindingResult err,@PathVariable("id") int id) {		
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		if(tuyen.getTenXe().trim().length() == 0 ) {
			err.rejectValue("tenXe","tuyen","Tên xe không được bỏ trống");
		}
		if(tuyen.getDiemDi().trim().length() == 0) {
			err.rejectValue("diemDi","tuyen","Điểm đi không được bỏ trống");
		}
		if(tuyen.getDiemDen().trim().length() == 0) {
			err.rejectValue("diemDen","tuyen","Điểm đến không được bỏ trống");
		}
		if(tuyen.getThoiGian() ==  null) {
			err.rejectValue("thoiGian", "tuyen", "Vui lòng chọn ngày");
		}
		if(tuyen.getSoVe() <= 0) {
			err.rejectValue("soVe", "tuyen", "Số lượng vé không phải lớn hơn 0");
		}
		if(tuyen.getGiaVe() <= 0) {
			err.rejectValue("giaVe", "tuyen", "Gía vé phải lớn hơn 0");
		}
		if(err.hasErrors()) {
			md.addAttribute("message", "Vui lòng sửa lỗi sau");
		}
		else {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(tuyen);
				t.commit();
				md.addAttribute("message", "Sửa tuyến thành công");
			}catch(Exception ex){
				t.rollback();
				md.addAttribute("message", "Sửa tuyến thất bại");
			}finally {
				session.close();
			}
		}
		return "admin/order/insert-order";
	}
}
