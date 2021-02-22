package com.example.books.services;

import com.example.books.domain.Address;
import com.example.books.domain.City;
import com.example.books.domain.User;
import com.example.books.domain.enums.Rol;
import com.example.books.dto.RegisterNewUserDTO;
import com.example.books.dto.UserDTO;
import com.example.books.repositories.IAddressRepository;
import com.example.books.repositories.ICityRepository;
import com.example.books.repositories.IUserRepository;
import com.example.books.security.UserSS;
import com.example.books.services.exceptions.AuthorizationException;
import com.example.books.services.exceptions.DataIntegrityException;
import com.example.books.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder _bCryptPasswordEncoder;

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private ICityRepository _cityRepository;

    @Autowired
    private IAddressRepository _addressRepository;

    @Transactional
    public User insert(User user) {
        user.setId(null);
        user = _userRepository.save(user);
        _addressRepository.saveAll(user.getAddresses());
        return user;
    }

    public User findById(Integer id) {
        UserSS userAuth = AuthUserService.authenticated();

        if (userAuth == null || !userAuth.hasRole(Rol.ADMIN) && !id.equals(userAuth.getId())) {
            throw new AuthorizationException("Access Denied!");
        }

        Optional<User> user = _userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(String
                .format("User not found! Id: %d, Type: %s", id, User.class.getName())));
    }

    public List<User> findAll() {
        return _userRepository.findAll();
    }

    public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return _userRepository.findAll(pageRequest);
    }

    public User update(User user) {
        User newUser = _userRepository.findByEmail(user.getEmail());
        updateUser(newUser, user);
        return _userRepository.save(newUser);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            _userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot be deleted because there are ");
        }
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getLastName(), userDTO.getEmail(), null);
    }

    public User fromDTO(RegisterNewUserDTO registerNewUserDTO) {
        User user = new User(null, registerNewUserDTO.getName(), registerNewUserDTO.getLastName(),
                registerNewUserDTO.getEmail(), _bCryptPasswordEncoder.encode(registerNewUserDTO.getPassword()));

        City city = _cityRepository.findById(registerNewUserDTO.getCityId()).orElse(null);

        Address address = new Address(null, registerNewUserDTO.getStreet(), registerNewUserDTO.getOutsideNumber(),
                registerNewUserDTO.getInteriorNumber(), registerNewUserDTO.getZipCode(),
                registerNewUserDTO.getNeighborhood(), user, city);

        user.setAddresses(Arrays.asList(address));

        return user;
    }

    private void updateUser(User newClient, User user) {
        newClient.setName(user.getName());
        newClient.setLastName(user.getLastName());
        newClient.setEmail(user.getEmail());
    }
}
