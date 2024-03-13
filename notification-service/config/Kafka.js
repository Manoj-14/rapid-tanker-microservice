const { Kafka, logLevel } = require("kafkajs");
const { NodeMail } = require("./Mail");
const mail = new NodeMail();
class CustomKafka {
  #kafka;
  constructor(broker, mechanism, username, password) {
    this.#kafka = new Kafka({
      brokers: [broker],
      ssl: true,
      sasl: {
        mechanism: mechanism,
        username: username,
        password: password,
      },
      logLevel: [logLevel.ERROR],
    });
  }

  subscribe = async (topic, groupId, fn) => {
    const consumer = this.#kafka.consumer({ groupId: groupId });
    await consumer
      .subscribe({
        topic: topic,
        fromBeginning: false,
      })
      .then((data) => {
        console.log("Subscribed to group", groupId);
      })
      .catch((err) => {
        console.log(`Error in group ${groupId} - ${err}`);
      });

    await consumer
      .run({
        eachMessage: async ({ topic, partition, message }) => {
          console.log("Message received at topic ", topic);
          await fn(JSON.parse(message.value.toString()));
        },
      })
      .catch((err) => {
        console.log(`Error in topic ${topic} - ${err}`);
      });
  };
}
module.exports = { CustomKafka };
