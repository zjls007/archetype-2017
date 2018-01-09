package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.common.constant.Constants;
import com.cy.dao.AttachmentDAO;
import com.cy.dao.system.MenuInfoDAO;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.Attachment;
import com.cy.entity.system.UserInfo;
import com.cy.service.MenuInfoService;
import com.cy.service.UserInfoService;
import com.cy.web.controller.admin.base.BaseController;
import com.cy.web.dto.param.system.ModifyPwdDTO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.activemq.util.ByteArrayInputStream;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by zxj on 2017/11/29.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private MenuInfoDAO menuInfoDAO;

    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AttachmentDAO attachmentDAO;

    @RequestMapping({"/", "index"})
    public String index(ModelMap modelMap) {
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getSession().getAttribute(Constants.CURRENT_USER);
        modelMap.addAttribute("userInfo", userInfo);

        modelMap.addAttribute("menuInfoList", menuInfoService.list());
        List<Long> menuIdList;
        if (subject.hasRole(Constants.ROLE_SYS_ADMIN) || subject.hasRole(Constants.DEVELOPER)) {
            menuIdList = menuInfoDAO.getAllIdList();
        } else {
            menuIdList = userInfoDAO.getMenuIdList(getCurrentUserId());
        }
        modelMap.addAttribute("hasMenuIdList", menuIdList);
        return "index";
    }

    @RequestMapping({"home"})
    public String home(ModelMap modelMap) {
        return "home";
    }

    @RequestMapping("timeOut")
    public String timeOut() {
        return "timeOut";
    }

    @RequestMapping("exception")
    public String exception() {
        return "exception";
    }

    @GetMapping("modifyPwd")
    public String modifyPwd() {
        return "userInfo/modifyPwd";
    }

    @PostMapping("modifyPwd")
    @ResponseBody
    public Response modifyPwd(ModifyPwdDTO dto) {
        userInfoService.modifyPwd(dto, getCurrentUserId());
        return new Response();
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("upload")
    @ResponseBody
    public Response fileUpload(@RequestParam("file") CommonsMultipartFile file) throws Exception {
        Attachment attachment = new Attachment();
        attachment.setData(file.getBytes());
        attachment.setCreateUserId(getCurrentUserId());

        // 计算图片MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(attachment.getData());
        BigInteger bigInt = new BigInteger(1, digest.digest());
        String md5 = bigInt.toString(16);
        if (attachmentDAO.getById(md5) == null) {
            attachment.setId(md5);
            attachmentDAO.insert(attachment);
        }
        return new Response(md5);
    }

    @RequestMapping("img/{id}")
    public void getImg(@PathVariable String id, HttpServletResponse response) throws Exception {
        // http://blog.csdn.net/linzhiqiang0316/article/details/51330372
        Attachment attachment = attachmentDAO.getById(id);
        if (attachment != null && attachment.getData() != null) {
            BufferedImage b = ImageIO.read(new MemoryCacheImageInputStream(new ByteArrayInputStream(attachment.getData())));
            ServletOutputStream outputStream = response.getOutputStream();
            Thumbnails.of(b).size(140, 160).keepAspectRatio(false).outputFormat("png").toOutputStream(outputStream);
            outputStream.flush();
            outputStream.close();
        }
    }

}
