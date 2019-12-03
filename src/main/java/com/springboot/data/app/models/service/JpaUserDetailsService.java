package com.springboot.data.app.models.service;

import org.springframework.stereotype.Service;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService{// implements UserDetailsService{

//	@Autowired
//	private UserRepository userRepository;
//	
//	private Logger logger  = LoggerFactory.getLogger(JpaUserDetailsService.class);
//	
//	@Override
//	@Transactional(readOnly=true)
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		Usuario usuario = userRepository.findByUsername(username);
//		
//		if(usuario == null) {
//			logger.error("Error login : el usuario "+username+" no se encuentra registrado");
//			throw new UsernameNotFoundException("el usuario no existe");
//		}
//		
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		
//		for(Role role : usuario.getRoles()) {
//			logger.info("Role: ".concat(role.getAuthority()));
//			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
//		}
//		
//		if(authorities.isEmpty()) {
//			logger.error("Error login : el usuario "+username+"no tiene roles asignados, no puede iniciar sesion");
//			throw new UsernameNotFoundException("el usuario no tiene roles");
//		}
//		
//		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
//	}

}
