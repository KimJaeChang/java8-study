package kr.co.kjc.java8_study.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//@SpringBootTest
//@ActiveProfiles("local")
class CollectionServiceTest {

  CollectionService collectionService = new CollectionService();

  @Test
  void splitIteratorRunTest() {
    collectionService.splitIteratorRun();
  }

  @Test
  void comparatorRunTest() {
    collectionService.comparatorRun();
  }

}