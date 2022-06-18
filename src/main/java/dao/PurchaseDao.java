package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Purchase;
import util.HibernateUtil;

public class PurchaseDao {

    public void savePurchase(Purchase purchase) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(purchase);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Update Purchase
     * @param Purchase
     */
    public void updatePurchase(Purchase purchase) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(purchase);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Delete Purchase
     * @param id
     */
    public void deletePurchase(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a Purchase object
            Purchase purchase = session.get(Purchase.class, id);
            if (purchase != null) {
                session.delete(purchase);
                System.out.println("Purchase is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get Purchase By ID
     * @param id
     * @return
     */
    public Purchase getPurchase(int id) {

        Transaction transaction = null;
        Purchase purchase = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an Purchase object
            purchase = session.get(Purchase.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return purchase;
    }

    /**
     * Get all Purchases
     * @return
     */
    @SuppressWarnings("unchecked")
    public List <Purchase> getAllPurchase() {

        Transaction transaction = null;
        List < Purchase > listOfPurchase = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an Purchase object

            listOfPurchase = session.createQuery("from Purchase").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfPurchase;
    }
}