package com.skilldistillery.skillguild.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillguild.entities.Member;
import com.skilldistillery.skillguild.entities.MemberId;

public interface MemberRepository extends JpaRepository<Member, MemberId>{

}
