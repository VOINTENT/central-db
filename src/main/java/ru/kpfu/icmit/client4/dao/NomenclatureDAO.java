package ru.kpfu.icmit.client4.dao;

import ru.kpfu.icmit.client4.model.Metric;
import ru.kpfu.icmit.client4.model.Nomenclature;
import ru.kpfu.icmit.client4.util.DbWork;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NomenclatureDAO {
    public Nomenclature create(Nomenclature object) {
        try {
            // Запрос вставки нового кортежа
            final String SQL_INSERT = "INSERT INTO nomenclature(uuid, product_name, create_date, modify_date, metric, relevant) VALUES (?, ?, ?, ?, ?, ?)";

            // Обращаемся к объекту БД
            DbWork db = DbWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);

            // Устанавливаем значения в запрос
            preparedStatement.setString(1, object.getUid().toString());
            preparedStatement.setString(2, object.getProductName());
            preparedStatement.setTimestamp(3, object.getCreateDate());
            preparedStatement.setTimestamp(4, object.getModifyDate());
            preparedStatement.setString(5, object.getMetric().toString());
            preparedStatement.setBoolean(6, true);

            // Выполняем запрос
            preparedStatement.executeUpdate();

            // Возвращаем объект вставки
            return object;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    public Nomenclature update(Nomenclature object) {
        try {
            // Запрос обновления данных кортежа
            final String SQL_UPDATE = "UPDATE nomenclature SET product_name = ?, create_date = ?, modify_date = ?, metric = ?, relevant = ? WHERE uuid = ?";

            // Обращаемся к объекту БД
            DbWork db = DbWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            // Устанавливаем значение в запрос
            preparedStatement.setString(1, object.getProductName());
            preparedStatement.setTimestamp(2, object.getCreateDate());
            preparedStatement.setTimestamp(3, object.getModifyDate());
            preparedStatement.setString(4, object.getMetric().toString());
            preparedStatement.setBoolean(5, object.getRelevant());
            preparedStatement.setString(6, object.getUid().toString());

            // Выполняем запрос
            preparedStatement.executeUpdate();
            return object;
        } catch (SQLException e) {
            return null;
        }
    }

    public Nomenclature delete(Nomenclature object) {
        try {
            // Запрос на удаление кортежа
            final String SQL_DELETE = "DELETE FROM nomenclature WHERE uuid = ?";

            // Обращаемся к объекту БД
            DbWork db = DbWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);

            // Устанавливаем значение в запрос
            preparedStatement.setString(1, object.getUid().toString());

            // Выполняем запрос
            preparedStatement.executeUpdate();
            preparedStatement.close();

            return object;
        } catch (SQLException e) {
            return null;
        }
    }

    public Nomenclature getByUuid(String uuid) {
        try {
            // Запрос поиска кортежа по id
            final String SQL_SELECT_BY_ID = "SELECT * FROM nomenclature WHERE uuid = ?";

            // Обращаемся к объекту БД
            DbWork db = DbWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);

            // Устанавливаем значение в запрос
            preparedStatement.setString(1, uuid);

            // Выполняем запрос
            ResultSet resultSet = preparedStatement.executeQuery();

            // Чтение результатов запроса
            Nomenclature nomenclature = null;
            if (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                Timestamp createDate = resultSet.getTimestamp("create_date");
                Timestamp modifyDate = resultSet.getTimestamp("modify_date");
                String metric = resultSet.getString("metric");
                Boolean relevant = resultSet.getBoolean("relevant");

                nomenclature = new Nomenclature(UUID.fromString(uuid), productName, createDate, modifyDate, new Metric("0001", metric), relevant);
            }

            // Возвращаем результат
            return nomenclature;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Nomenclature> getAll() {
        try {
            // Запрос поиска всех кортежей
            final String SQL_SELECT = "SELECT * FROM nomenclature";

            // Обращаемся к объекту БД
            DbWork db = DbWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            Statement statement = connection.createStatement();

            // Выполняем запрос
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            // Чтение результатов поиска
            ArrayList<Nomenclature> nomenclatures = new ArrayList<>();
            while (resultSet.next()) {
                String uuid = resultSet.getString("uuid");
                String productName = resultSet.getString("product_name");
                Timestamp createDate = resultSet.getTimestamp("create_date");
                Timestamp modifyDate = resultSet.getTimestamp("modify_date");
                String metric = resultSet.getString("metric");
                Boolean relevant = resultSet.getBoolean("relevant");

                nomenclatures.add(new Nomenclature(UUID.fromString(uuid), productName, createDate, modifyDate, new Metric("0001", metric), relevant));
            }

            // Возвращаем результат
            return nomenclatures;
        } catch (SQLException e) {
            return null;
        }
    }
}
