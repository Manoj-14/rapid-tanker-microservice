const client = require("cloud-config-client");
const express = require("express");
const {
  sendSupplierRegisterSuccessfullMsg,
  sendUserRegisterSuccessfullMsg,
} = require("./middleware/middleware.js");
const { CustomKafka } = require("./config/Kafka.js");
const { registerWithEureka } = require("./config/eureka-client.js");
const app = express();
require("dotenv").config();
client
  .load({
    application: "notification-service",
    endpoint: `http://${process.env.HOST_NAME}:8888`,
  })
  .then((config) => {
    process.env.port = config.get("server.port");
    broker = config.get("sasl.broker");
    saslMechanism = config.get("sasl.mechanism");
    saslUsername = config.get("sasl.username");
    saslPassword = config.get("sasl.password");
    process.env.node_email = config.get("nodemailer.email");
    process.env.app_password = config.get("nodemailer.password");

    registerWithEureka("notification-service", process.env.port);

    const kafka = new CustomKafka(
      broker,
      saslMechanism,
      saslUsername,
      saslPassword
    );

    kafka.subscribe(
      "notification",
      "notification_group",
      sendSupplierRegisterSuccessfullMsg
    );

    kafka.subscribe(
      "user-service",
      "user_notification_group",
      sendUserRegisterSuccessfullMsg
    );

    app.listen((port = process.env.port || 8080), () => {
      console.log("Server is running on port", port);
    });
  })
  .catch((err) => {
    console.log(err);
    process.exit(1);
  });
