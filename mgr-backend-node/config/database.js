const Sequelize = require('sequelize');
DATABASE_URL = 'postgres://postgres_node:postgres_node@127.0.0.1:5433/performance_node';
const database = new Sequelize(DATABASE_URL);

module.exports = database;