const Sequelize = require('sequelize');
const {QueryTypes} = require('sequelize');
const database = require('../config/database');

const DatabaseOperation = database.define(
    'db_operations',
    {
        description: {
            type: Sequelize.TEXT
        },
        number: {
            type: Sequelize.INTEGER
        }
    },
    { timestamps: true }
);

DatabaseOperation.sync({ force: true });

DatabaseOperation.getTimeConsumingOperation = async (req, res) => {
    try {
        let {seconds} = req.params;
        let t0 = process.hrtime();
        await database.query("SELECT pg_sleep(?)",
            {
                replacements: [seconds],
                type: QueryTypes.SELECT
            });
        let t1 = process.hrtime(t0);
        res.send(`Execution time: ${t1[0]}s ${t1[1] / 1000000}ms`);
    } catch (error) {
        console.log(error);
        res.status(500).send(error);
    }
};

module.exports = DatabaseOperation;