package com.springboot.data.app.models.data.transformer;

import org.springframework.stereotype.Component;

import com.springboot.data.app.models.data.entity.Inquilino;
import com.springboot.data.app.models.data.view.InquilinoView;

@Component
public class InquilinoTransformer {

	public Inquilino convetToInquilino(InquilinoView inquilinoView){
		
		Inquilino inquilino = new Inquilino();
		
		if(inquilinoView != null) {
			inquilino.setId(inquilinoView.getId());
			inquilino.setNombre(inquilinoView.getNombre());
			inquilino.setApellido(inquilinoView.getApellido());
			inquilino.setEmail(inquilinoView.getEmail());
			inquilino.setCreateAt(inquilinoView.getCreateAt());
			inquilino.setTipo(inquilinoView.getTipo());
		}
		
		return inquilino;	
	}
	
public InquilinoView convetToInquilinoView(Inquilino inquilino){
		
		InquilinoView inquilinoView = new InquilinoView();
		
		if(inquilino != null) {
			inquilinoView.setId(inquilino.getId());
			inquilinoView.setNombre(inquilino.getNombre());
			inquilinoView.setApellido(inquilino.getApellido());
			inquilinoView.setEmail(inquilino.getEmail());
			inquilinoView.setCreateAt(inquilino.getCreateAt());
			inquilinoView.setTipo(inquilino.getTipo());
		}
		
		return inquilinoView;
		
	}
}
