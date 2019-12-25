package ru.kpfu.icmit.client4.api;

import ru.kpfu.icmit.client4.model.Metric;
import ru.kpfu.icmit.client4.model.Nomenclature;
import ru.kpfu.icmit.client4.util.soap.Body;
import ru.kpfu.icmit.client4.util.soap.Envelope;
import ru.kpfu.icmit.client4.util.soap.Header;
import ru.kpfu.icmit.client4.util.soap.XmlList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

public class NomenclatureApi {

    public Nomenclature create(Nomenclature nomenclature) {
        try {
            URL url = new URL("http://185.20.227.163:8080/server4/nomenclature/add");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setDoOutput(true);

            String payload = createEnvelope(nomenclature);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int status_code = connection.getResponseCode();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Envelope envelope = (Envelope) unmarshaller.unmarshal(new ByteArrayInputStream(response.toString().getBytes(Charset.defaultCharset())));
                Nomenclature nomenclatureNew = (Nomenclature) envelope.getBody().getContent();
                return nomenclatureNew;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Nomenclature> getAll() {
        try {
            URL url = new URL("http://185.20.227.163:8080/server4/nomenclature/getAll");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setDoOutput(false);

            int status_code = connection.getResponseCode();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Envelope envelope = (Envelope) unmarshaller.unmarshal(new ByteArrayInputStream(response.toString().getBytes(StandardCharsets.UTF_8)));
                XmlList<Nomenclature> xmlList = (XmlList<Nomenclature>) envelope.getBody().getContent();
                List<Nomenclature> nomenclatures = xmlList.getList();
                return nomenclatures;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Nomenclature getByUuid(String uuid) {
        try {
            URL url = new URL("http://185.20.227.163:8080/server4/nomenclature/getOneByUid");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = uuid.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int status_code = connection.getResponseCode();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Envelope envelope = (Envelope) unmarshaller.unmarshal(new ByteArrayInputStream(response.toString().getBytes(Charset.defaultCharset())));
                Nomenclature nomenclature = (Nomenclature) envelope.getBody().getContent();
                return nomenclature;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Nomenclature> getAllByModifyDateAfter(String date) {
        try {
            URL url = new URL("http://185.20.227.163:8080/server4/nomenclature/getAll");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setDoOutput(false);

            int status_code = connection.getResponseCode();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Envelope envelope = (Envelope) unmarshaller.unmarshal(new ByteArrayInputStream(response.toString().getBytes(StandardCharsets.UTF_8)));
                XmlList<Nomenclature> xmlList = (XmlList<Nomenclature>) envelope.getBody().getContent();
                List<Nomenclature> nomenclatures = xmlList.getList();
                return nomenclatures;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createEnvelope(Nomenclature nomenclature) {

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        body.setContent(nomenclature);
        try {
            JAXBContext context = JAXBContext.newInstance(Envelope.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(envelope, stringWriter);
            return stringWriter.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
