import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig, MatDialogModule } from '@angular/material/dialog';
import { Message } from '../../../models/message.model';
import { UserContextMenuComponent } from '../../contexte-menus/user-context-menu/user-context-menu.component';
import { UserContextService } from '../../../services/user-context-service.service';

@Component({
  selector: 'app-message-user',
  imports: [MatDialogModule],
  templateUrl: './message-user.component.html',
  styleUrl: './message-user.component.scss'
})
export class MessageUserComponent {
  @Input() message!: Message;

  constructor(private dialog: MatDialog) {}

  onAuthorClick(event: MouseEvent): void {
    this.openDialog(event.clientX, event.clientY);
  }

  openDialog(positionX: number, positionY: number) {

    const dialogConfig = new MatDialogConfig();
    //dialogConfig.position = { left: `${positionX}px`, top: `${positionY}px` };
    dialogConfig.data = this.message.userId;
    console.log("Dialog data : " + dialogConfig.data);
    this.dialog.open(UserContextMenuComponent, dialogConfig);
  }
}
