package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.SigninInfoDto;
import com.qlsv5.entity.ERole;
import com.qlsv5.entity.RoleEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.payload.request.LoginRequest;
import com.qlsv5.payload.request.SignupRequest;
import com.qlsv5.payload.response.JwtResponse;
import com.qlsv5.payload.response.MessageResponse;
import com.qlsv5.repository.RoleRepository;
import com.qlsv5.repository.TokenRefreshTokenPairRepository;
import com.qlsv5.repository.UserRepository;
import com.qlsv5.security.jwt.JwtUtils;
import com.qlsv5.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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




//	@PostMapping("/logout")
//	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
//
//		// Xóa thông tin liên quan đến người dùng khỏi phiên làm việc hiện tại
//		SecurityContextHolder.clearContext();
//
//		// Lấy mã token (JWT) từ header Authorization
//		String jwt = jwtUtils.parseJwtFromRequest(request);
//
//		if (jwt != null) {
//			// Xóa mã thông báo truy cập và mã thông báo làm mới
//			jwtUtils.deleteJwtTokens(jwt);
//		}
//
//		// Xóa cookie liên quan đến phiên làm việc của người dùng
//		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler("JSESSIONID");
//		cookieClearingLogoutHandler.logout(request, response, null);
//
//		return ResponseEntity.ok(new MessageResponse("Đăng xuất thành công!"));
//	}

//	@PostMapping("/logout/admin")
//	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
//
//		ReturnObject returnObject = new ReturnObject();
//		try {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//			if (auth != null) {
//				new SecurityContextLogoutHandler().logout(request, response, auth);
//			}
//
//
//			returnObject.setStatus(ReturnObject.SUCCESS);
//			returnObject.setMessage("200");
//			returnObject.setRetObj("Logout successful!");
//		}
//		catch (Exception ex){
//			returnObject.setStatus(ReturnObject.ERROR);
//			returnObject.setMessage(ex.getMessage());
//		}
//
//
//		return ResponseEntity.ok(returnObject);
//	}

//	@PostMapping("/signout")
//	public ResponseEntity<?> logoutUser() {
//		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//				.body(new MessageResponse("You've been signed out!"));
//	}

//	@PostMapping("/logout")
//	public ResponseEntity<?> logout() {
//
//		SecurityContextHolder.clearContext();
//		return ResponseEntity.ok("Logout success!");
//
////		ReturnObject returnObject = new ReturnObject();
////		try {
////
////			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////			if (auth != null) {
////				new SecurityContextLogoutHandler().logout(null, null, auth);
////			}
////
////			returnObject.setStatus(ReturnObject.SUCCESS);
////			returnObject.setMessage("200");
////
////			returnObject.setRetObj("Logout successful!");
////		}
////		catch (Exception ex){
////			returnObject.setStatus(ReturnObject.ERROR);
////			returnObject.setMessage(ex.getMessage());
////		}
////
////		return ResponseEntity.ok(returnObject);
//
//	}

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
