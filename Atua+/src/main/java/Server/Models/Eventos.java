	package Server.Models;
	
	import java.io.Serializable;
	import java.util.ArrayList;
	import java.util.List;
	
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.JoinTable;
	import javax.persistence.ManyToMany;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
	@Entity
	@Table(name = "Eventos")
	public class Eventos implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	
		private Integer id;
		private String titulo;
		private String descricao;
		
		@ManyToOne
		@JoinColumn(name = "institutos_id")
		private Institutos institutos;
		
		@ManyToMany
		@JoinTable(name = "eventos_patrocinador", joinColumns = @JoinColumn(name = "eventos_id"), inverseJoinColumns = @JoinColumn(name = "patrocinador_id"))	
		private List<Patrocinador> patrocinador;
	
		@ManyToMany
		@JoinTable(name = "eventos_tag", joinColumns = @JoinColumn(name = "eventos_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
		private List<Tag> tag;
	
		public Institutos getInstituto() {
			return institutos;
		}
	
		public void setInstituto(Institutos instituto) {
			this.institutos = instituto;
		}
	
		public List<Tag> getTag() {
			return tag;
		}
	
		public void setTag(Tag tag) {
			this.tag.add(tag);
		}
	
		public void setPatrocinador(Patrocinador patrocinador) {
			this.patrocinador.add(patrocinador);
		}
	
		public List<Patrocinador> getPatrocinador() {
			return patrocinador;
		}
	
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
	
		public String getTitulo() {
			return titulo;
		}
	
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
	
		public String getDescricao() {
			return descricao;
		}
	
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	
		public Eventos() {
			super();
		}
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((institutos == null) ? 0 : institutos.hashCode());
			result = prime * result + ((patrocinador == null) ? 0 : patrocinador.hashCode());
			result = prime * result + ((tag == null) ? 0 : tag.hashCode());
			result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Eventos other = (Eventos) obj;
			if (descricao == null) {
				if (other.descricao != null)
					return false;
			} else if (!descricao.equals(other.descricao))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (institutos == null) {
				if (other.institutos != null)
					return false;
			} else if (!institutos.equals(other.institutos))
				return false;
			if (patrocinador == null) {
				if (other.patrocinador != null)
					return false;
			} else if (!patrocinador.equals(other.patrocinador))
				return false;
			if (tag == null) {
				if (other.tag != null)
					return false;
			} else if (!tag.equals(other.tag))
				return false;
			if (titulo == null) {
				if (other.titulo != null)
					return false;
			} else if (!titulo.equals(other.titulo))
				return false;
			return true;
		}
	
		
	}
