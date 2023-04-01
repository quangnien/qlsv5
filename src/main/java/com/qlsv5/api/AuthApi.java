package com.qlsv5.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.SigninInfoDto;
import com.qlsv5.entity.ERole;
import com.qlsv5.entity.RoleEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.payload.request.LoginRequest;
import com.qlsv5.payload.request.SignupRequest;
import com.qlsv5.payload.response.MessageResponse;
import com.qlsv5.repository.RoleRepository;
import com.qlsv5.repository.TokenRefreshTokenPairRepository;
import com.qlsv5.repository.UserRepository;
import com.qlsv5.security.jwt.JwtUtils;
import com.qlsv5.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthApi {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private TokenRefreshTokenPairRepository tokenRefreshTokenPairRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		ReturnObject returnObject = new ReturnObject();
		try {
			// Xác thực thông tin đăng nhập của người dùng
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			// Đặt đối tượng xác thực vào bảo mật context
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Tạo mã thông báo truy cập (JWT)
			String jwt = jwtUtils.generateJwtToken(authentication);


			/* new */
//			// Tạo mã thông báo làm mới
//			String refreshToken = jwtUtils.generateRefreshToken(authentication);
//
//			// Lưu trữ mã thông báo làm mới vào cơ sở dữ liệu
//			tokenRefreshTokenPairRepository.save(new RefreshJwtToken(refreshToken));
//
//			// Thiết lập các thông tin JWT vào header của phản hồi
//			HttpHeaders responseHeaders = new HttpHeaders();
//			responseHeaders.set("Authorization", "Bearer " + jwt);
//			responseHeaders.set("RefreshToken", refreshToken);
			/*end new*/

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			returnObject.setStatus(ReturnObject.SUCCESS);
			returnObject.setMessage("200");

			SigninInfoDto results = new SigninInfoDto();
			results.setJwt(jwt);
			results.setRoles(roles);
			results.setUserDetails(userDetails);

			returnObject.setRetObj(results);
		}
		catch (Exception ex){
			returnObject.setStatus(ReturnObject.ERROR);
			returnObject.setMessage(ex.getMessage());
		}

		return ResponseEntity.ok(returnObject);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> signOut(HttpServletRequest request) {
		ReturnObject returnObject = new ReturnObject();
		try {

			// Lấy mã thông báo làm mới từ request
//			String refreshToken = jwtUtils.getRefreshTokenFromRequest(request);

			// Xóa mã thông báo làm mới khỏi cơ sở dữ liệu
//			tokenRefreshTokenPairRepository.deleteByRefreshToken(refreshToken);

			// Xóa đối tượng xác thực trong bảo mật context
			SecurityContextHolder.getContext().setAuthentication(null);

			SecurityContextHolder.clearContext();

			returnObject.setStatus(ReturnObject.SUCCESS);
			returnObject.setMessage("Đăng xuất thành công");
		} catch (Exception ex) {
			returnObject.setStatus(ReturnObject.ERROR);
			returnObject.setMessage(ex.getMessage());
		}

		return ResponseEntity.ok(returnObject);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		UserEntity user = new UserEntity(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<RoleEntity> roles = new HashSet<>();

		if (strRoles == null) {
			RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
//				case "mod":
//					RoleEntity modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
				default:
					RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
