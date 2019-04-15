package com.zerotoproduction.bucketlist.controller;

import com.zerotoproduction.bucketlist.exception.ResourceNotFoundException;
import com.zerotoproduction.bucketlist.model.BucketList;
import com.zerotoproduction.bucketlist.repository.BucketRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value="Bucketlist Management System", description="Operations pertaining to buckets in Bucket lists Management System")
@RequestMapping("/api/v1")
public class BucketListController {

    @Autowired
    BucketRepository bucketRepository;

    @GetMapping("/buckets/all")
    @ApiOperation(value = "View a list of available buckets")
    public ResponseEntity index() {
        return ResponseEntity.ok(bucketRepository.findAll());
    }

    @GetMapping("/buckets")
    @ApiOperation(value = "View a single Bucket", response = BucketList.class)
    public BucketList getBucket(@RequestParam(value="id") Long id)  throws ResourceNotFoundException{
        Optional<BucketList> foundBucketList = bucketRepository.findById(id);

        if(foundBucketList.isPresent()){
            return foundBucketList.get();
        }else {
            throw new ResourceNotFoundException("No bucket with specified id " + id + " found");
        }
    }

    @PostMapping("/buckets")
    @ApiOperation(value = "Add a bucket", response = BucketList.class)
    public BucketList addToBucketList(
            @ApiParam(value = "Bucket name ", required = true) @RequestParam(value="name") String name,
            @ApiParam(value = "Bucket description", required = true) @RequestParam(value="description") String desc) {
        return bucketRepository.save(new BucketList(name, desc));
    }


    @PutMapping("/buckets")
    @ApiOperation(value = "Update a bucket")
    public BucketList updateBucketList(
            @ApiParam(value = "Bucket name ", required = true) @RequestParam(value="name") String name,
            @ApiParam(value = "Bucket id", required = true) @RequestParam(value="id") Long id,
            @ApiParam(value = "Bucket description", required = true) @RequestParam(value="description") String desc)
            throws ResourceNotFoundException {
        Optional<BucketList> optionalBucketList = bucketRepository.findById(id);
        if(!optionalBucketList.isPresent()){
            throw new ResourceNotFoundException("No bucket with specified id " + id + " found");
        }

        BucketList foundBucketList = optionalBucketList.get();
        foundBucketList.setName(name);
        foundBucketList.setDescription(desc);
        return bucketRepository.save(foundBucketList);
    }

    @DeleteMapping("/buckets")
    @ApiOperation(value = "delete a bucket")
    public ResponseEntity removeBucketList(
            @ApiParam(value = "Bucket id", required = true) @RequestParam(value="id") Long id) {
        bucketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
