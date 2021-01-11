package core.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class describes Company entity
 * 
 * @autor Daniel Sorkin
 * 
 *        last update 2021-1-11
 */
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@OneToMany
	private List<Coupon> coupons;

	/**
	 * Constructor creates Company instance
	 * 
	 * @param String name
	 * @param String email
	 * @param String password
	 */
	public Company(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Get method returns a company id
	 * 
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set method for a company id. You should use the method to set company id you
	 * get from database.
	 * 
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get method returns a company name
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set method for a company name
	 * 
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get method returns a company email
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set method for a company email
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get method returns a company password
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set method for a company password
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get method returns all coupons belong to a company
	 * 
	 * @return List<Coupon> coupons
	 */
	public List<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * Set method for collection of the coupons belonging to a company
	 */
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
