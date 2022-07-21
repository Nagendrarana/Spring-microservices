import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmailService } from 'src/app/service/email.service';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {
  data = {
    to:"",
    subject:"",
    message:""

  }
  flag=false
  
  constructor(private email:EmailService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }
  datatransfer(){
    if(this.data.to=="" || this.data.subject=="" || this.data.message==""){
      
      this.snack.open("Enter All fields","ok")
      return;
    }
    this.flag=true
    this.email.sendEmail(this.data).subscribe(
      Response=>{
        this.flag=false
        console.log(Response)
      },
      Error=>{
        this.flag=false
        console.log(Error)
      }
    )
  }
 

}
