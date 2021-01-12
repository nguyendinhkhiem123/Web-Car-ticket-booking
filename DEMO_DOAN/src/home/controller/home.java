package home.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import bean.LoginBean;
import bean.SearchBean;
import mail.Mailer;
import poly.entity.Members;
import poly.entity.Tuyens;
import poly.entity.Ve;

@Transactional
@Controller
public class home {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;

	@RequestMapping(value = "home/{id}", method = RequestMethod.GET)
	public String home(@PathVariable("id") int id , ModelMap md) {
		Session  session1 = factory.getCurrentSession();
		String hql ="from Members s where s.iD=:id";
		Query query = session1.createQuery(hql);
		query.setParameter("id", id);
		List<Members> ds = query.list();
		
		md.addAttribute("id", id);
		md.addAttribute("hovaten",ds.get(0).getHovaten());
		md.addAttribute("image", ds.get(0).getImage());
		return "user/home";
	}

	@RequestMapping(value = "home/{id}/book", method = RequestMethod.GET)
	public String getBook(@PathVariable("id") int id,ModelMap md) {
		Session  session1 = factory.getCurrentSession();
		String hql ="from Members s where s.iD=:id";
		Query query = session1.createQuery(hql);
		query.setParameter("id", id);
		List<Members> ds = query.list();
		
		md.addAttribute("id", id);
		md.addAttribute("hovaten",ds.get(0).getHovaten());
		md.addAttribute("image", ds.get(0).getImage());
		
		md.addAttribute("message","Vui lòng chọn đầy đủ thông tin");
		return "user/error";
	}
	@RequestMapping(value = "home/{id}/book",method = RequestMethod.POST)
	public String book(ModelMap md,
			@RequestParam("Thoigian") String thoiGian,
			@RequestParam("Diemdi") String diemDi,
			@RequestParam("Diemden") String diemDen,
			@PathVariable("id") int id
		
			) throws ParseException {
		Session  session1 = factory.getCurrentSession();
		String hql1 ="from Members s where s.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id", id);
		List<Members> ds1 = query1.list();
		
		md.addAttribute("id", id);
		md.addAttribute("hovaten",ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
	
		if(diemDi.trim().length() == 0 || diemDen.trim().length() == 0 || thoiGian.length() ==0)
		{
			md.addAttribute("message","Vui lòng chọn đầy đủ thông tin");
			return "user/error";
		}
		else {
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(thoiGian);  
			Session session = factory.getCurrentSession();
			String hql ="from Tuyens s where s.diemDi=:di and s.diemDen=:den and s.thoiGian=:gian";
			Query query = session.createQuery(hql);
			query.setParameter("di", diemDi);
			query.setParameter("den",diemDen);
			query.setParameter("gian",date1);
			List<Tuyens> ds = query.list();
			if(ds.size() == 0) {
				md.addAttribute("message", "Không có tuyến xe đi từ " +diemDi+ " đến "+diemDen+" vào ngày "+thoiGian);
				return "user/error";
			}
			else {
				md.addAttribute("message", "Danh sách tuyến xe đi từ " +diemDi+ " đến "+diemDen+" vào ngày "+thoiGian);
				md.addAttribute("ds", ds);
				return "user/book";
			}
		}
	}
	
	@RequestMapping(value = "home/{id}/cancel/{maVe}/{maTuyen}", params ="lnkDelete")
	public String Cancel(ModelMap md,@PathVariable("id") int id, @PathVariable("maVe") int maVe,@PathVariable("maTuyen") int maTuyen) {
		Session  session1 = factory.getCurrentSession();
		String hql ="from Members s where s.iD=:id";
		Query query = session1.createQuery(hql);
		query.setParameter("id", id);
		List<Members> ds = query.list();
		md.addAttribute("hovaten",ds.get(0).getHovaten());
		md.addAttribute("image", ds.get(0).getImage());
		md.addAttribute("id",id);
		
		Session  session2 = factory.getCurrentSession();
		String hql1 ="from Tuyens s where s.maTuyen=:id";
		Query query1 = session2.createQuery(hql1);
		query1.setParameter("id", maTuyen);
		List<Tuyens> ds1 = query1.list();
		
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Ve v = new Ve();
		v.setMaVe(maVe);
		try {
			session.delete(v);
			t.commit();
			md.addAttribute("message", "Hủy vé thành công");
		}
		catch(Exception e) {
			t.rollback();
			md.addAttribute("message", "Hủy vé thất bại");
		}
		finally {
			session.close();
		}
		try {
			String from ="nguyendinhkhiem12a4@gmail.com";
			String to = ds.get(0).getEmail();
			String subject ="Thông đặt vé xe";
			String body ="Qúy khách "+ds.get(0).getHovaten() + " đã HỦY 1 vé xe đi ở xe "
					+ds1.get(0).getTenXe()+" vào ngày "+ new Date().toString() +" đi từ "+ds1.get(0).getDiemDi() +" đến "+ds1.get(0).getDiemDen();
					
			mailer.Send(from, to, subject, body);
			md.addAttribute("message", "BẠN ĐÃ HỦY VÉ. VUI LÒNG CHECK MAIL");
	
		}catch(Exception ex) {
			md.addAttribute("message", "HỦY VÉ THẤT BẠI. VUI LÒNG CHECK MAIL");
			
		}
		return "user/list-order-erorr";
	}
	@RequestMapping(value = "home/{id}/book/{matuyen}/{soluong}", params = "lnkInsert")
	public String bookTicket(ModelMap md, @PathVariable("id") int id, @PathVariable("matuyen") int matuyen,@PathVariable("soluong") int soluong) throws ParseException {
		
		Date k = new Date();
		Session  session1 = factory.getCurrentSession();
		String hql ="from Members s where s.iD=:id";
		Query query = session1.createQuery(hql);
		query.setParameter("id", id);
		List<Members> ds = query.list();
		md.addAttribute("hovaten",ds.get(0).getHovaten());
		md.addAttribute("image", ds.get(0).getImage());
		
		Session  session2 = factory.getCurrentSession();
		String hql1 ="from Tuyens s where s.maTuyen=:id";
		Query query1 = session2.createQuery(hql1);
		query1.setParameter("id", matuyen);
		List<Tuyens> ds1 = query1.list();
		
		Session session =  factory.openSession();
		Transaction t = session.beginTransaction();
		Ve ve = new Ve();
		ve.setIdUser(ds.get(0));
		ve.setMaTuyen(ds1.get(0));
		ve.setNgayDat(new Date());
		ve.setSoLuongVe(soluong);
		
		try {
			session.save(ve);
			t.commit();
			
		}catch(Exception ex) {
			md.addAttribute("id",id);
			md.addAttribute("matuyen", matuyen);
			md.addAttribute("ds", ds1);
			md.addAttribute("message1", "Đặt vé thất bại.");
			t.rollback();
			return "user/book";
		}
		finally {
			session.close();
		}	
		
		try {
		
			
			String from ="nguyendinhkhiem12a4@gmail.com";
			String to = ds.get(0).getEmail();
			String subject ="Thông đặt vé xe";
			String body ="Qúy khách "+ds.get(0).getHovaten() + " có đặt 1 vé xe đi ở xe "
					+ds1.get(0).getTenXe()+" vào ngày "+ k.toString() +" đi từ "+ds1.get(0).getDiemDi() +" đến "+ds1.get(0).getDiemDen()+" với giá vé là "
					+ds1.get(0).getGiaVe() + " đi vào ngày "+ds1.get(0).getThoiGian() 
					+". Xin quý khách vui lòng chú ý";
			mailer.Send(from, to, subject, body);
			md.addAttribute("id",id);
			md.addAttribute("matuyen", matuyen);
			md.addAttribute("message1", "Đặt vé thành công. Vui lòng check mail");
			md.addAttribute("ds", ds1);
			return "user/book";
			
		}catch(Exception ex)
		{
			md.addAttribute("id",id);
			md.addAttribute("matuyen", matuyen);
			md.addAttribute("ds", ds1);
			md.addAttribute("message1", "Đặt vé thất bại.");
			return "user/book";
		}

	
	}
}
