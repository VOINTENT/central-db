package ru.kpfu.icmit.client4;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.icmit.client4.api.NomenclatureApi;
import ru.kpfu.icmit.client4.dao.NomenclatureDAO;
import ru.kpfu.icmit.client4.model.Metric;
import ru.kpfu.icmit.client4.model.Nomenclature;
import ru.kpfu.icmit.client4.repository.NomenclatureRepository;
import ru.kpfu.icmit.client4.service.NomenclatureService;
import ru.kpfu.icmit.client4.util.DbWork;
import ru.kpfu.icmit.client4.util.soap.Body;
import ru.kpfu.icmit.client4.util.soap.Envelope;
import ru.kpfu.icmit.client4.util.soap.Header;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        NomenclatureApi nomenclatureApi = new NomenclatureApi();
        NomenclatureDAO nomenclatureDAO = new NomenclatureDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Просмотреть все номенклатуры");
            System.out.println("2. Синхронизироваться с центральной БД");
            System.out.println("3. Создать новую номенклатуру");
            System.out.print("Выберите следующее действие: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                List<Nomenclature> nomenclatures = nomenclatureDAO.getAll();
                for (int i = 0; i < nomenclatures.size(); i++) {
                    System.out.println(nomenclatures.get(i));
                }
                System.out.println();
            } else if (choice == 2) {
                List<Nomenclature> nomenclaturesRemote = nomenclatureApi.getAll();
                List<Nomenclature> nomenclaturesLocal = nomenclatureDAO.getAll();

                System.out.println("Добавлены следующие номенклатуры: ");
                for(int i = 0; i < nomenclaturesRemote.size(); i++){
                    Nomenclature nomenclature = nomenclaturesRemote.get(i);
                    UUID uuid = nomenclature.getUid();
                    if(nomenclatureDAO.getByUuid(uuid.toString()) == null){
                        nomenclatureDAO.create(nomenclature);
                        System.out.println(nomenclature);
                    }
                }

//                System.out.println("Загружены следующие номенклатуры: ");
//                for (int i = 0; i < nomenclaturesLocal.size(); i++) {
//                    Nomenclature nomenclature = nomenclaturesLocal.get(i);
//                    UUID uuid = nomenclature.getUid();
//                    if (nomenclatureApi.getByUuid(uuid.toString()) == null) {
//                        nomenclatureApi.create(nomenclature);
//                        System.out.println(nomenclature);
//                    }
//                }

                System.out.println();
            } else if (choice == 3) {
                System.out.print("Название продукта: ");
                String productName = scanner.next();
                System.out.print("Метрика: ");
                String metric = scanner.next();
                Nomenclature nomenclature = new Nomenclature(productName, new Metric("001", metric));
                Nomenclature new_nomenclature = nomenclatureApi.create(nomenclature);
                nomenclatureDAO.create(new_nomenclature);
                System.out.println(new_nomenclature);
            }
        }
    }
}
