package ac.customer.service;

import ac.customer.model.Client;
import ac.customer.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    /*// Хранилище клиентов - еслим пока без БД
    private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();
    // Переменная для генерации ID клиента
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();*/

//    А это объект для работы с базой
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> read(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if (clientRepository.existsById(id)){
            client.setId(id);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (clientRepository.existsById(id)){
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
