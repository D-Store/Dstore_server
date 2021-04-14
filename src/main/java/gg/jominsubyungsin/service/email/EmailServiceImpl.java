package gg.jominsubyungsin.service.email;

import gg.jominsubyungsin.domain.dto.email.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendMail(EmailDto emailDto) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");
      messageHelper.setFrom(emailDto.getSenderMail(), emailDto.getSenderName());
      messageHelper.setTo(emailDto.getReceiveMail());
      messageHelper.setSubject(emailDto.getSubject());
      // 이메일 본문 (인코딩을 해야 한글이 깨지지 않음)
      messageHelper.setText(emailDto.getMessage(), true);

      javaMailSender.send(msg);
    } catch (Exception e) {
      e.printStackTrace();
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 에러");
    }
  }

}