const nodemailer = require("nodemailer");
class NodeMail {
  #transporter = nodemailer.createTransport({
    service: "Gmail",
    host: "smtp.gmail.com",
    port: 465,
    secure: true,
    auth: {
      user: process.env.node_email,
      pass: process.env.app_password,
    },
  });

  sendMail = (to, content) => {
    const mailOptions = {
      from: `Manoj M <${process.env.node_email}>`,
      to: to,
      content: content,
    };
    this.#transporter.sendMail(mailOptions, (error, info) => {
      if (error) {
        console.error("Error sending email: ", error);
      } else {
        console.log("Email sent: ", info.response);
      }
    });
  };
}

module.exports = { NodeMail };
