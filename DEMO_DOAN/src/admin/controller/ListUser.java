package admin.controller;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.bcel.internal.generic.NEW;

import jdk.nashorn.internal.ir.annotations.Reference;
import poly.entity.Members;

@Transactional
@Controller
@RequestMapping("/admin/")
public class ListUser {
	String image,role;
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	@RequestMapping(value ="{id}/list-user")
	public String admin(ModelMap md, @PathVariable("id") int id) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		Session session = factory.getCurrentSession();
		String hql = "from Members s";
		Query query = session.createQuery(hql);
		List<Members> ds = query.list();
		md.addAttribute("list",ds);
		return "admin/list-user";
	}
	@RequestMapping(value="{id}/list-user/delete/{id1}", params = "lnkDelete")
	public String delete(ModelMap md, @PathVariable("id") int id, @PathVariable("id1") int id1) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		Members s = new Members();
		s.setiD(id1);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(s);
			t.commit();
			md.addAttribute("message","Đã xóa User thành công");
			
		}catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			md.addAttribute("message", "Xóa user thất bại !. User đã đặt vé rồi");
		}
		finally {
			session.close();
		}
		return "admin/home";
	}
	@RequestMapping(value = "{id}/list-user/update/{id1}",params = "lnkUpdate", method = RequestMethod.GET)
	public String getUpdate(ModelMap md, @PathVariable("id1") int id1,@PathVariable("id") int id) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		Session session = factory.getCurrentSession();
		String hql = "from Members s where s.iD=:id1";
		Query query = session.createQuery(hql);
		query.setParameter("id1", id1);
		List<Members> ds = query.list();
		Members mem = ds.get(0);
		md.addAttribute("mem",mem);
		image = mem.getImage();
		role = mem.getRole();
		return "admin/user/update-user";
	}
	@RequestMapping(value = "{id}/list-user/update", method = RequestMethod.POST)
	public String Update(ModelMap md, @ModelAttribute ("mem") Members mem, BindingResult err,@PathVariable("id") int id,@RequestParam("file") MultipartFile file) 
	{
		Session session2= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session2.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		if(mem.getHovaten().trim().length() == 0) {
			err.rejectValue("hovaten","mem","Vui lòng nhập Họ và tên");
		}
		if(mem.getSdt().trim().length() == 0) {
			err.rejectValue("sdt","mem","Vui lòng nhập số điện thoại");
		}
		else if(mem.getSdt().trim().length() < 10 ||mem.getSdt().trim().length() > 11 ) {
			err.rejectValue("sdt","mem","Số điện thoại là 10 số hoặc 11 số ");
		}
		else {
			try {
				int k = Integer.parseInt(mem.getSdt());
			}
			catch(Exception ex) {
				err.rejectValue("sdt","mem","Vui lòng nhập số ");
			}
		}
		if(mem.getAddress().trim().length() == 0) {
			err.rejectValue("address","mem","Vui lòng nhập địa chỉ");
		}
		if(mem.getEmail().trim().length() == 0) {
			err.rejectValue("email","mem","Vui lòng nhập email");
		}
		if(file.isEmpty()) {
			mem.setImage(image);
			
		}
		else {
			if(file.getSize() > 20971520) {
				err.rejectValue("image","mem","Ảnh đã quá dung lượng trên 2MB");
			}
			else {
				if(file.getContentType().equalsIgnoreCase("image/png") || file.getContentType().equalsIgnoreCase("image/jpg") || file.getContentType().equalsIgnoreCase("image/jpeg"))
				{
					Random rd = new Random();
					int number = rd.nextInt(10000);
					try {
						String path = context.getRealPath("/image/" +number+file.getOriginalFilename());
						file.transferTo(new File(path));
						mem.setImage(number+file.getOriginalFilename());
					}catch(Exception ex) {
						
					}
				}
				else {
					err.rejectValue("image","mem","tệp chọn không phải ảnh vui lòng chọn lại");
				}
			}
		}
		if(err.hasErrors()) {
				md.addAttribute("message", "Vui lòng sửa lỗi sau !");
		}
		else
		{	
				Session session1 = factory.openSession();
				Transaction t = session1.beginTransaction();
				try {
					mem.setRole(role);
					session1.update(mem);
					t.commit();
					md.addAttribute("message", "Sửa thành công");
				}catch(Exception ex) {
					t.rollback();
					md.addAttribute("message", "Sửa thất bại");
				}
				finally {
					session1.close();
				}	
		}
		return "admin/user/update-user";
	}
	@RequestMapping(value = "{id}/list-user/insert", method = RequestMethod.GET)
	public String getInsert(ModelMap md, @PathVariable("id") int id) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		md.addAttribute("mem",new Members());
		return "admin/user/insert-user";
	}
	@RequestMapping(value = "{id}/list-user/insert", method = RequestMethod.POST)
	public String Insert(ModelMap md, @ModelAttribute("mem") Members mem, BindingResult err,@PathVariable("id") int id,@RequestParam("file") MultipartFile file)
	{
		Session session2= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session2.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		if(mem.getUserName().trim().length() == 0) {
			err.rejectValue("userName","mem", "Vui lòng nhập username");
		}
		if(mem.getUserName().trim().length() == 0) {
			err.rejectValue("passWord","mem","Vui lòng nhập password");
		}
		if(mem.getHovaten().trim().length() == 0) {
			err.rejectValue("hovaten","mem","Vui lòng nhập Họ và tên");
		}
		if(mem.getSdt().trim().length() == 0) {
			err.rejectValue("sdt","mem","Vui lòng nhập số điện thoại");
		}
		else if(mem.getSdt().trim().length() < 10 ||mem.getSdt().trim().length() > 11 ) {
			err.rejectValue("sdt","login","Số điện thoại là 10 số hoặc 11 số ");
		}
		else {
			try {
				int k = Integer.parseInt(mem.getSdt());
			}
			catch(Exception ex) {
				err.rejectValue("sdt","mem","Vui lòng nhập số ");
			}
		}
		if(mem.getAddress().trim().length() == 0) {
			err.rejectValue("address","mem","Vui lòng nhập địa chỉ");
		}
		if(mem.getEmail().trim().length() == 0) {
			err.rejectValue("email","mem","Vui lòng nhập email");
		}
		if(file.isEmpty()) {
			err.rejectValue("image", "mem", "Vui lòng chọn ảnh");
		}
		else {
			if(file.getSize() > 20971520) {
				err.rejectValue("image","mem","Ảnh đã quá dung lượng trên 2MB");
			}
			else {
				if(file.getContentType().equalsIgnoreCase("image/png") || file.getContentType().equalsIgnoreCase("image/jpg") || file.getContentType().equalsIgnoreCase("image/jpeg"))
				{
					Random rd = new Random();
					int number = rd.nextInt(10000);
					try {
						String path = context.getRealPath("/image/" +number+file.getOriginalFilename());
						file.transferTo(new File(path));
						mem.setImage(number+file.getOriginalFilename());
					}catch(Exception ex) {
						
					}
				}
				else {
					err.rejectValue("image","mem","tệp chọn không phải ảnh vui lòng chọn lại");
				}
			}
		}
		if(err.hasErrors()) {
				md.addAttribute("message", "Vui lòng sửa lỗi sau !");
		}
		else
		{	Session session = factory.getCurrentSession();
			String hql ="from Members s where s.userName=:username";
			Query query = session.createQuery(hql);
			query.setParameter("username", mem.getUserName());
			List<Members> ds = query.list();
			if(ds.size() !=0) {
				err.rejectValue("userName","mem", "Username đã được sử dụng");
				md.addAttribute("message", "Vui lòng sửa lỗi sau !");
			}
			else {
				Session session1 = factory.openSession();
				Transaction t = session1.beginTransaction();
				try {
					
					session1.save(mem);
					t.commit();
					md.addAttribute("message", "Thêm thành công");
				}catch(Exception ex) {
					t.rollback();
					md.addAttribute("message", "Thêm thất bại");
				}
				finally {
					session1.close();
				}
				
			}
			
		}
		return "admin/user/insert-user";
	}
}
