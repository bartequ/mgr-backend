const Sequelize = require('sequelize');
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

module.exports = DatabaseOperation;