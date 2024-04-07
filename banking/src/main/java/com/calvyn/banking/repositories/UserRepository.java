package com.calvyn.banking.repositories;

import com.calvyn.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // UTILISATION REQUETTE SDP (Spring Date Pattern) QUERY NOMMAGE

    // select * from _user where firstname ='calvyn'; / * = List or Set .
    List<User> findByFirstname(String firstname);

    //select * from _user where firstname like "%calvyn%";
    List<User> findByFirstnameContaining(String string);

    //select * from _user where firstname ilike "%calvyn%"; //pas sensible à la case./ insensible à la casse
    List<User> findByFirstnameContainingIgnoreCase(String string);

    //select * from _user u inner join account a on u.id = a.id_user and a.iban ='DE1234567' //iban ici
    List<User> findByAccountIban(String iban);

    //select * from _user where firstname ='%calvyn%' and email ='calvynlebonheur@gmail.com'
    User findByFirstnameContainingIgnoreCaseAAndEmail(String firstname); // retour un simplen objet

    //UTILISER QUERY AVEC JPQL
    @Query("from User where firstname = :fn") //namipule l'entite pas la table
    List<User> searchByFirstname(@Param("fn") String firstname);

    @Query("from User where firstname = '%:firstname%'") //namipule l'entite pas la table /requet with like
    List<User> searchByFirstnameContaining( String firstname);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban(@Param("iban") String iban);

    @Query(value = "select * from _user u inner join account a on u.id = a.user_id and a.iban = :iban", nativeQuery = true)
    List<User> searchByIbanNative(@Param("iban") String iban);


}
