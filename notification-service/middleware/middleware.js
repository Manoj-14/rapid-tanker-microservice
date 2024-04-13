const { NodeMail } = require("../config/Mail");

const sendSupplierRegisterSuccessfullMsg = (data) => {
  const { email, name } = data.supplier;
  const mail = new NodeMail();
  mail.sendMail(email, name);
};

const sendUserRegisterSuccessfullMsg = (data) => {
  const { email } = data.user;
  const mail = new NodeMail();
  mail.sendMail(email, "User registered successful");
};

module.exports = { sendSupplierRegisterSuccessfullMsg, sendUserRegisterSuccessfullMsg };
