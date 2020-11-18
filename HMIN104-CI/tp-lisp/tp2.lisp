; 1 Paramètres optionnels et APPLY

(defvar l1 '(1 2 3 (4 5) 6) )

(defun my_member(x l &optional test)
  (if (atom l) nil 
    (if (eql x (car l)) l 
      (my_member x (cdr l)) )))

(defun my_member2(x &key l)
  (if (atom l) nil 
    (if (eql x (car l)) l 
      (my_member2 x (cdr l)) )))
;; (print (my_member(3 2 '())))
;; (print (my_member2 2 '(1 2 3)))

;; (defun my_member3(x l :test 
;;   (if (atom l) nil 
;;     (if (eql x (car l)) l 
;;       (my_member3 x (cdr l)) )))

; 2. fonctions d'arité variable et APPLY

;; (defun my_append (l1 l2)
;;   (if (equal (cdr l1) nil) (append l1 l2)
;;     (my_append (cdr l1) l2) ))


; 3. Fonctions destructives sur les listes

(defun conc (l1 l2 &optional &rest ln)
  (print '(test coucou)) )
(print (conc 1 2 3 4 5))