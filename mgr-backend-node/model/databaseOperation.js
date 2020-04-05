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

//alter: true for update
DatabaseOperation.sync({ force: true });

DatabaseOperation.readAllOperations = async (req, res) => {
    try {
        const users = await DatabaseOperation.findAll();
        return res.send({ users });
    } catch (error) {
        return res.send(error);
    }
};

DatabaseOperation.readOperationById = async (req, res) => {
    const { id } = req.params;
    DatabaseOperation.findByPk(id)
        .then((db_operation) => {
            if (db_operation) {
                res.send(db_operation);
            } else {
                res.status(404).send();
            }
        })
};

DatabaseOperation.createOperation = async (req, res) => {
    DatabaseOperation.create(req.body)
        .then((db_operation) => {
            if (db_operation) {
                res.send(db_operation);
            } else {
                res.status(500).send();
            }
        })
};

DatabaseOperation.updateDatabaseOperation = async (req, res) => {
    let { id } = req.params;
    DatabaseOperation.update(req.body, {
        where: { id: id }
    }).then((db_operation) => {
        if (db_operation[0] !== 0) {
            res.send("Resource updated");
        } else {
            res.status(404).send("Post not found");
        }
    })
};

DatabaseOperation.deleteDatabaseOperation = async (req, res) => {
  let { id }  = req.params;
  DatabaseOperation.destroy({
      where: { id: id }
  }).then( () => {
      res.status(204).send("Resource deleted");
  }).catch((err) => {
      res.send(500).send(err.message);
  })
};

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

DatabaseOperation.getAllWithoutOrm = async (req, res) => {
   try {
       const query = await pg.query();
       res.send(query)
   } catch (e) {
       res.status(500).send(e);
   }
};

module.exports = DatabaseOperation;