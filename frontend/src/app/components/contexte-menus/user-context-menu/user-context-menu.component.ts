import { Component, OnInit, OnDestroy, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogContent, MatDialog, MatDialogActions } from '@angular/material/dialog';
import { UserContextService } from '../../../services/user-context-service.service';
import { User } from '../../../models/user.model';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-context-menu',
  templateUrl: './user-context-menu.component.html',
  styleUrls: ['./user-context-menu.component.scss'],
  imports: [MatDialogContent, MatDialogActions]
})
export class UserContextMenuComponent implements OnInit {

  userId:string;
  user: User | null = null;

  constructor(
    private userContextService: UserContextService,
    private dialogRef: MatDialogRef<UserContextMenuComponent>, @Inject(MAT_DIALOG_DATA) data: any){
      this.userId = data;
  }

  ngOnInit() {
    this.userContextService.getUserInfo(this.userId).subscribe(
      data => {
        this.user = new User(data.id, data.name, data.numberMessagesSent, data.followedAt, data.totalSubGifted);
      },
      error => {
        console.error('Erreur lors de la récupération des informations utilisateur', error);
      }
    );
  }

  close() {
    this.dialogRef.close();
  }

  banUser() {
    this.userContextService.banUser(this.userId, "TOO BAD").subscribe(
      response => {console.log(response)},
      error => {console.log(error)}
    );
    this.close();
  }

  timeoutUser() {
    this.userContextService.timeoutUser(this.userId, "120", "TOO BAD").subscribe(
      response => {console.log(response)},
      error => {console.log(error)}
    );
    this.close();
  }

  warnUser() {
    console.log("warn user !");
  }
}
