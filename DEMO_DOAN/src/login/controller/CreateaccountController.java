package login.controller;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.entity.Members;


@Transactional
@Controller
public class CreateaccountController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	@RequestMapping(value ="/createaccount" ,method = RequestMethod.GET)
	public String index(ModelMap md)
	{
		md.addAttribute("login",new Members());
		return "login/createaccount";
	}
	@RequestMapping(value ="/createaccount" ,method = RequestMethod.POST)
	public String index1(ModelMap md, @ModelAttribute("login") Members login,BindingResult err, @RequestParam("file") MultipartFile file)
	{
		if(login.getUserName().trim().length() == 0) {
			err.rejectValue("userName","login", "Vui lòng nhập username");
		}
		if(login.getUserName().trim().length() == 0) {
			err.rejectValue("passWord","login","Vui lòng nhập password");
		}
		if(login.getHovaten().trim().length() == 0) {
			err.rejectValue("hovaten","login","Vui lòng nhập Họ và tên");
		}
		if(login.getSdt().trim().length() == 0) {
			err.rejectValue("sdt","login","Vui lòng nhập số điện thoại");
		}
		else if(login.getSdt().trim().length() < 10 ||login.getSdt().trim().length() > 11 ) {
			err.rejectValue("sdt","login","Số điện thoại là 10 số hoặc 11 số ");
		}
		else {
			try {
				int k = Integer.parseInt(login.getSdt());
			}
			catch(Exception ex) {
				err.rejectValue("sdt","login","Vui lòng nhập số ");
			}
		}
		if(login.getAddress().trim().length() == 0) {
			err.rejectValue("address","login","Vui lòng nhập địa chỉ");
		}
		if(login.getEmail().trim().length() == 0) {
			err.rejectValue("email","login","Vui lòng nhập email");
		}
		if(file.isEmpty()) {
			err.rejectValue("image", "login", "Vui lòng chọn ảnh");
		}
		else {
			if(file.getSize() > 20971520) {
				err.rejectValue("image","login","Ảnh đã quá dung lượng trên 2MB");
			}
			else {
				if(file.getContentType().equalsIgnoreCase("image/png") || file.getContentType().equalsIgnoreCase("image/jpg") || file.getContentType().equalsIgnoreCase("image/jpeg"))
				{
					Random rd = new Random();
					int number = rd.nextInt(10000);
					try {
						String path = context.getRealPath("/image/" +number+file.getOriginalFilename());
						file.transferTo(new File(path));
						login.setImage(number+file.getOriginalFilename());
					}catch(Exception ex) {
						
					}
				}
				else {
					err.rejectValue("image","login","tệp chọn không phải ảnh vui lòng chọn lại");
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
			query.setParameter("username", login.getUserName());
			List<Members> ds = query.list();
			if(ds.size() !=0) {
				err.rejectValue("userName","login", "Username đã được sử dụng");
				md.addAttribute("message", "Vui lòng sửa lỗi sau !");
			}
			else {
				Session session1 = factory.openSession();
				Transaction t = session1.beginTransaction();
				try {
					login.setRole("user");
					session1.save(login);
					t.commit();
					md.addAttribute("message", "Tạo tài khoản thành công");
				}catch(Exception ex) {
					t.rollback();
					md.addAttribute("message", "Tạo thất bại");
				}
				finally {
					session1.close();
				}
				
			}
			
		}
		return "login/createaccount";
	}
}
