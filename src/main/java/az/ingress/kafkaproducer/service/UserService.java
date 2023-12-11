package az.ingress.kafkaproducer.service;

import az.ingress.kafkaproducer.dto.UserDto;
import az.ingress.kafkaproducer.dto.UserResponseDto;
import az.ingress.kafkaproducer.dto.UserUpdateBalanceDto;
import az.ingress.kafkaproducer.model.User;
import az.ingress.kafkaproducer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {
    private final KafkaTemplate<String ,Object> kafkaTemplate;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void register(UserDto userDto){
        userRepository.save(modelMapper.map(userDto, User.class)); //!!!!!!!
         kafkaTemplate.send("user-register",userDto);

    }

    public UserResponseDto addBalance(Long id, UserUpdateBalanceDto dto){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        user.setBalance(user.getBalance()+ dto.getBalance());
        userRepository.save(user);
        kafkaTemplate.send("user-update-balance",user);
        return modelMapper.map(user,UserResponseDto.class);
    }
}
