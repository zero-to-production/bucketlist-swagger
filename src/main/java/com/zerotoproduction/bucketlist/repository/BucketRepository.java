package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.model.BucketList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketList, Long> {
}
