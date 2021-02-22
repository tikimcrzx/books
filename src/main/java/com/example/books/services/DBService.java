package com.example.books.services;

import com.example.books.domain.*;
import com.example.books.domain.enums.Rol;
import com.example.books.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder _bCryptPasswordEncoder;

    @Autowired
    private IStateRepository _stateRepository;

    @Autowired
    private ICityRepository _cityRepository;

    @Autowired
    private IBookRepository _bookRepository;

    @Autowired
    private IAddressRepository _addressRepository;

    @Autowired
    private IUserRepository _userRepository;

    public void databaseInstance() throws ParseException {
        State state00 = new State(null, "Sinaloa", null);
        State state01 = new State(null, "Nayarit", null);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        City city00 = new City(null, "Culiacan", state00);
        City city01 = new City(null, "Mazatlan", state00);
        City city02 = new City(null, "Tepic", state01);
        City city03 = new City(null, "Tuxpan", state01);

        state00.setCities(Arrays.asList(city00, city01));
        state01.setCities(Arrays.asList(city02, city03));

        Book book00 = new Book(null, "un final perfecto",
                "https://1.bp.blogspot.com/-z_Q7KSDdfwU/UNPD0LaA4yI/AAAAAAAAHJg/_AH5BsCvX-I/s1600/c230427c303c0684b5582388f5d0dfd7_XL.jpg",
                "john katzenbach", sdf.parse("20/08/2014"), "978-8466652193");

        User user00 = new User(null, "Rossette Petit", "Le Portrait de",
                "tikimioo723@gmail.com", _bCryptPasswordEncoder.encode("tikio"));
        user00.addRole(Rol.ADMIN);

        Address address00 = new Address(null, "Florencia", "15-A", null, 789,
                "Ciudad del Valle", user00, city00);

        user00.setAddresses(Arrays.asList(address00));

        _stateRepository.saveAll(Arrays.asList(state00, state01));
        _cityRepository.saveAll(Arrays.asList(city00, city01, city02, city03));
        _userRepository.save(user00);
        _addressRepository.save(address00);
        _bookRepository.save(book00);
    }
}
